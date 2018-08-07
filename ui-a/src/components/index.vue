<template>
  <div id="mydiv">
    <Table stripe :columns="columns1" :data="data1"></Table>
    <iframe id="uib" :src="path" frameborder="0"></iframe>
  </div>
</template>
<script>
  import request from './request.js'
  import vc from 'vue-cookie'

    export default {
        data () {
            return {
                columns1: [
                    {
                        title: 'Name',
                        key: 'name'
                    },
                    {
                        title: 'Age',
                        key: 'age'
                    },
                    {
                        title: 'Address',
                        key: 'address'
                    }
                ],
                data1: [
                    {
                        name: 'John Brown',
                        age: 18,
                        address: 'New York No. 1 Lake Park',
                        date: '2016-10-03'
                    },
                    {
                        name: 'Jim Green',
                        age: 24,
                        address: 'London No. 1 Lake Park',
                        date: '2016-10-01'
                    },
                    {
                        name: 'Joe Black',
                        age: 30,
                        address: 'Sydney No. 1 Lake Park',
                        date: '2016-10-02'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    }
                ],
              path: 'http://nzl.fangxinqian.cn:8088/addcookie?cookieName=logincookie&cookieValue='
            }
        } ,
          created () {
            console.log('begin')
             request.post('/check').then((res) => {
                  if (res.data.code === 20) {
                    console.log(res.data.content)
                  } else {
                    this.$router.push('/login')
                  }
                })
            console.log('end')
            this.setCookie()
          },
          methods: {
            setCookie() {
              var val = vc.get('logincookie');
              this.path +=val

              console.log(this.path)
            }
          }

    }
</script>
