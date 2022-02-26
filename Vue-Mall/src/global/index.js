let bindToGlobal = (obj, key='var') => {
  if (typeof window[key] === 'undefined') {
    window[key] = {};
  }

  for (let i in obj) {
    if(obj.hasOwnProperty(i)){
      window[key][i] = obj[i]
    }
  }
}

/*http://mall.qiudb.top*/
/*http://127.0.0.1:9999*/

bindToGlobal({
  adminRole:'超级管理员',
  productRole:'商品管理员',
  orderRole:'订单管理员',
  userRole:'人事管理员',
  ordinaryRole:'顾客',
  http:'http://127.0.0.1:9999'
}, 'VAR');

// 使用端口号：  9999 3306 3307 3308 6379 994
