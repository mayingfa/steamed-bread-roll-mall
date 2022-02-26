//防止按钮短时间内重复点击
export default {
  install (Vue) {
    Vue.directive('preventReClick', {
      inserted(el, binding) {
        el.addEventListener('click', () => {
          if(!el.disabled) {
            el.disabled = true;
            setTimeout(() => {
              el.disabled = false;
            }, binding.value || 500);
          }
        })
      }
    })
  }
}
