<template>
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
        <FormItem prop="user">
            <Input type="text" v-model="formInline.username" placeholder="Username">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem prop="password">
            <Input type="password" v-model="formInline.password" placeholder="Password">
                <Icon type="ios-locked-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem>
            <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
        </FormItem>
    </Form>
</template>
<script>
  import request from './request.js'
    export default {
        data () {
            return {
                formInline: {
                    username: 'admin',
                    password: 'admin'
                },
                ruleInline: {
                    user: [
                        { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                        { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
                    ]
                }
            }
        },
        created () {
           request.post('/check').then((res) => {
                if (res.data.code === 20) {
                   this.$router.push('/')
                }
              })
        },
        methods: {
            handleSubmit(name) {
            request.post('/login', this.formInline).then((res) => {
                      if (res.data.code === 20) {
                        console.log(res.data.content)
                      }
                    })
              this.$router.push('/')
            }
        }
    }
</script>
