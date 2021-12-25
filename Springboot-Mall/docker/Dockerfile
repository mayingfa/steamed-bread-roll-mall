# 该镜像需要依赖的基础镜像
FROM java:8
# 指定维护者的名字
MAINTAINER Dt
# 将当前目录下的jar包复制到docker容器的/目录下
ADD breadroll-mall.jar /app.jar
# 运行过程中创建一个app.jar文件
RUN bash -c 'touch /app.jar'
# 声明服务运行在9999端口
EXPOSE 9999
# 指定docker容器启动时运行jar包
ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar --jasypt.encryptor.password=$jasypt_password"]
