// Pages/mobileAddUser/mobileAddUser.js
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    tel: '',
    nick_name: '',
    password: '',
    picture: '',
    role: '',
  },
  mobileblur: function(e) {
    var tel = e.detail.value.trim();
    if (tel != '') {
      this.setData({
        disabled: false,
        btnstate: 'primary',
        tel: tel,
      });
      console.log("tel:" + tel);
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default',
        tel: '',
      });
    }
  },
  nicknameblur: function (e) {
    var nick_name = e.detail.value;
    if (nick_name != '') {
      this.setData({
        nick_name: nick_name
      });
      console.log("nick_name:" + nick_name);
    }
  },
  passwordblur: function (e) {
    var password = e.detail.value;
    if (password != '') {
      this.setData({
        password: password
      });
      console.log("password:" + password);
    }
  },
  roleblur: function (e) {
    var role = e.detail.value;
    if (role != '') {
      this.setData({
        role: role
      });
      console.log("role:" + role);
    }
  },
  addUser: function(e) {
    console.log(e);
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/app/user/addUser',
      data: {
        tel: that.data.tel,
        nick_name: that.data.nick_name,
        password: that.data.password,
        role: that.data.role
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },

      success: function(res) {
        console.log(res.data);
        var message = res.data.msg;
        if (message == null) {
          var toastText = "获取数据失败";
          wx.showToast({
            title: toastText,
            icon: '/images/ac.png',
            duration: 2000
          })

        } else if (message == "程序内部错误") {
          wx.showToast({
            title: "失败，用户名或手机号重复",
            icon: 'none',
            duration: 2000
          })
        } else {
          wx.showToast({
            title: "注册成功",
            icon: 'success',
            duration: 2000
          })
          wx.navigateBack({
            url: '/Pages/login/login'
          })
        }
      }
    })
  }

})