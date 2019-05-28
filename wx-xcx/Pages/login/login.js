// pages/login/login.js
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    account: '',
    password: '',
    message: ''
  },

  accountInput: function(event) {
    var content = event.detail.value.trim();
    if (content !== '') {
      this.setData({
        disabled: false,
        btnstate: 'primary',
        account: content
      });
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default'
      });
    }
  },

  pwdBlur: function(e) {
    var password = e.detail.value;
    if (password != '') {
      this.setData({
        password: password
      });
    }
  },

  btnLogin: function() {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/user/login',
      data: {
        login_name: that.data.account,
        password: that.data.password
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      
      success: function(res) {
        console.log(res.data)
        var message = res.data.msg;
        if (message == null) {
          var toastText = "获取数据失败";
          wx.showToast({
            title: toastText,
            icon:'/images/ac.png',
            duration:2000
          });

        }else if(message =="用户名或密码不正确"){
          that.setData({
            
          })
        }
        else{
          wx.navigateTo({
            url: '/Pages/selectRole/selectRole',
          })
        }
          
      
      }
    })
  }

})