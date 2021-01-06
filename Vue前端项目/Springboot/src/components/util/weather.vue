<template>
  <div id="tp-weather-widget"></div>
</template>

<script>
export default {
  name: "weather",
  mounted() {
    (function(a,h,g,f,e,d,c,b){b=function(){d=h.createElement(g);c=h.getElementsByTagName(g)[0];d.src=e;d.charset="utf-8";d.async=1;c.parentNode.insertBefore(d,c)};a["SeniverseWeatherWidgetObject"]=f;a[f]||(a[f]=function(){(a[f].q=a[f].q||[]).push(arguments)});a[f].l=+new Date();if(a.attachEvent){a.attachEvent("onload",b)}else{a.addEventListener("load",b,false)}}(window,document,"script","SeniverseWeatherWidget","//cdn.sencdn.com/widget2/static/js/bundle.js?t="+parseInt((new Date().getTime() / 100000000).toString(),10)));
    window.SeniverseWeatherWidget('show', {
      flavor: "slim",
      location: "WX4FBXXFKE4F",
      geolocation: true,
      language: "zh-Hans",
      unit: "c",
      theme: "auto",
      token: "d12ecad8-f040-495b-89f7-6bc107b89f91",
      hover: "enabled",
      container: "tp-weather-widget"
    })
  }
}
</script>

<style>

</style>
