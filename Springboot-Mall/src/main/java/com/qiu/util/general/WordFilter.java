package com.qiu.util.general;

import com.qiu.entity.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * 敏感词汇过滤替换为*
 */
@Slf4j
public class WordFilter {
    private static final String WORDS = "WORDS";
    private static final String REPLACE_CHAR = "*";
    private static final int MIN_MATCHT_YPE = 1;
    private static HashMap<String, Object> sensitiveWordMap;

    /**
     * 敏感词汇过滤替换为*     *
     *
     * @param text 待检测文字
     * @return 替换后文字
     */
    public static String replaceWords(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        // 缓存获取敏感词汇原记录
        List<String> words = Cache.get(WORDS);
        if (CollectionUtils.isEmpty(words)) {
            //读取敏感词汇文件，存入缓存
            words = readWordsFile();
            Cache.put(WORDS, words);
        }
        if (CollectionUtils.isEmpty(words)) {
            return text;
        }
        // 屏蔽敏感词汇
        return WordFilter.replaceSensitiveWord(words, text);
    }

    /**
     * 读取敏感词汇文件
     */
    private static List<String> readWordsFile() {
        List<String> list = new ArrayList<>();
        Resource resource = new DefaultResourceLoader().getResource("classpath:words.txt");
        try (
                InputStream inputStream = resource.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String txt;
            while (StringUtils.isNotBlank(txt = bufferedReader.readLine())) {
                String word = StringUtils.deleteWhitespace(StringUtils.replace(txt, "，", ","));
                list.addAll(Arrays.asList(StringUtils.split(word, ",")));
            }
        } catch (Exception e) {
            log.error("读取敏感词汇文件出错", e);
        }
        return list;
    }

    /**
     * 替换敏感字字符
     *
     * @param data 敏感字集合
     * @param txt  待检查文本
     */
    private static String replaceSensitiveWord(List<String> data, String txt) {
        if (sensitiveWordMap == null) {
            addSensitiveWord(data);
        }
        String resultTxt = txt;
        //获取所有的敏感词
        List<String> set = getSensitiveWord(txt);
        for (String s : set) {
            resultTxt = resultTxt.replaceAll(s, REPLACE_CHAR);
        }
        return resultTxt;
    }


    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
     * 说明：该方法来源于互联网
     */
    private static void addSensitiveWord(List<String> datas) {
        sensitiveWordMap = new HashMap(datas.size());
        Iterator<String> iterator = datas.iterator();
        Map<String, Object> now;
        Map now2;
        while (iterator.hasNext()) {
            now2 = sensitiveWordMap;
            String word = iterator.next().trim(); //敏感词
            for (int i = 0; i < word.length(); i++) {
                char keyWord = word.charAt(i);
                Object obj = now2.get(keyWord);
                if (obj != null) { //存在
                    now2 = (Map) obj;
                } else { //不存在
                    now = new HashMap<>(5);
                    now.put("isEnd", "0");
                    now2.put(keyWord, now);
                    now2 = now;
                }
                if (i == word.length() - 1) {
                    now2.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 获取内容中的敏感词
     * 说明：该方法来源于互联网
     *
     * @param text 内容
     */
    private static List<String> getSensitiveWord(String text) {
        List<String> words = new ArrayList<>();
        Map now = sensitiveWordMap;
        int count = 0; //初始化敏感词长度
        int start = 0; //标志敏感词开始的下标
        for (int i = 0; i < text.length(); i++) {
            char key = text.charAt(i);
            now = (Map) now.get(key);
            if (now != null) { //存在
                count++;
                if (count == 1) {
                    start = i;
                }
                if ("1".equals(now.get("isEnd"))) { //敏感词结束
                    now = sensitiveWordMap; //重新获取敏感词库
                    words.add(text.substring(start, start + count)); //取出敏感词，添加到集合
                    count = 0; //初始化敏感词长度
                }
            } else { //不存在
                now = sensitiveWordMap;//重新获取敏感词库
                if (count == 1 && WordFilter.MIN_MATCHT_YPE == 1) { //不最佳匹配
                    count = 0;
                } else if (count == 1 && WordFilter.MIN_MATCHT_YPE == 2) { //最佳匹配
                    words.add(text.substring(start, start + count));
                    count = 0;
                }
            }
        }
        return words;
    }

    private WordFilter() {

    }
}
