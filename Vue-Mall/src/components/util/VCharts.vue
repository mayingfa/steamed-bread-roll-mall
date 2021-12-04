<template>
  <ve-line
    :data="chartData"
    :colors="chartColor"
    :legend-visible="true"
    :loading="loading"
    :data-empty="dataEmpty"
    :extend="extend"
    :settings="chartSettings">
  </ve-line>
</template>
<script>

const DATA_FROM_BACKEND = {
  rows: [
    {date: '2018-11-01', orderCount: 10, orderAmount: 1093},
    {date: '2018-11-02', orderCount: 20, orderAmount: 2230},
    {date: '2018-11-03', orderCount: 33, orderAmount: 3623},
    {date: '2018-11-04', orderCount: 50, orderAmount: 6423},
    {date: '2018-11-05', orderCount: 80, orderAmount: 8492},
    {date: '2018-11-06', orderCount: 60, orderAmount: 6293},
    {date: '2018-11-07', orderCount: 20, orderAmount: 2293},
    {date: '2018-11-08', orderCount: 60, orderAmount: 6293},
    {date: '2018-11-09', orderCount: 50, orderAmount: 5293},
    {date: '2018-11-10', orderCount: 30, orderAmount: 3293},
    {date: '2018-11-11', orderCount: 20, orderAmount: 2293},
    {date: '2018-11-12', orderCount: 80, orderAmount: 8293},
    {date: '2018-11-13', orderCount: 100, orderAmount: 10293},
    {date: '2018-11-14', orderCount: 10, orderAmount: 1293},
    {date: '2018-11-15', orderCount: 40, orderAmount: 4293},

    {date: '2018-11-16', orderCount: 60, orderAmount: 6293},
    {date: '2018-11-17', orderCount: 50, orderAmount: 5293},
    {date: '2018-11-18', orderCount: 30, orderAmount: 3293},
    {date: '2018-11-19', orderCount: 20, orderAmount: 2293},
    {date: '2018-11-20', orderCount: 80, orderAmount: 8293},
    {date: '2018-11-21', orderCount: 100, orderAmount: 10293},
    {date: '2018-11-22', orderCount: 10, orderAmount: 1293},
    {date: '2018-11-23', orderCount: 40, orderAmount: 4293}

  ]
};

import 'v-charts/lib/style.css'
export default {
  name: 'VCharts',
  data () {
    return {
      chartSettings: {
        xAxisType: 'time',
        area: true,
        yAxisName: ['订单总数', '订单金额'],
        axisSite: {right: ['orderAmount']},
        labelMap: {'orderCount': '订单数量', 'orderAmount': '订单金额'}
      },
      chartData: {
        columns: ['date', 'orderCount', 'orderAmount'],
        rows: []
      },
      extend: {
        series: {
          symbolSize: 5,
          label: {
            normal: {
              show: true
            }
          }
        }
      },
      chartColor: ['#89dd47', '#3cabf4'],
      loading: false,
      dataEmpty: false
    }
  },
  created () {
    this.getData()
  },
  methods: {
    async getData(){
      this.chartData.rows = DATA_FROM_BACKEND.rows // 注意这里应该是接口给返回回来的数据，为了方便展示data结构，我这里用的是静态数据
      this.dataEmpty = false
    }
  }
}
</script>
