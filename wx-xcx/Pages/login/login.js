// pages/login/login.js
var app = getApp();
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    account: '',
    password: '',
    img_l: '',
    hasloged: false,
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
      url: 'http://localhost:8080/faceToChild/app/user/login',
      data: {
        login_name: that.data.account,
        password: that.data.password
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },

      success: function(res) {
        // console.log(res.data)
        var message = res.data.msg;
        if (message == null) {
          var toastText = "获取数据失败";
          wx.showToast({
            title: toastText,
            icon: '/images/ac.png',
            duration: 2000
          });

        } else if (message == "用户名或密码不正确") {
          wx.showToast({
            title: "失败，用户名或密码不正确",
            icon: 'none',
            duration: 2000
          });
        } else {
          wx.showToast({
            title: "登录成功",
            icon: 'success',
            duration: 2000
          });
          that.setData({
            hasloged: true
          });
          app.globalData.cookie = 'JSESSIONID=' + res.data.data["token"];
          // console.log(app.globalData.cookie);
          wx.request({
            url: 'http://localhost:8080/faceToChild/app/file/found',
            header: {
              'cookie': getApp().globalData.cookie
            },
            method: 'GET',
            success: function(res) {
              // console.log(res)
              if (res.statusCode === 200) {
                that.setData({
                  img_l: res.data.data['url']
                })
                // console.log(that.data.img_l);
                const downloadTask = wx.downloadFile({
                  url: 'http://localhost:8080/faceToChild' + that.data.img_l,
                  success: function(res) {
                    console.log(that.data.img_l);
                    if (res.statusCode === 200) {
                      var pages = getCurrentPages(); //当前页面栈
                      // console.log(pages.length);
                      if (pages.length >= 1) {
                        

                        var beforePage = pages[pages.length - 1]; //获取上一个页面实例对象        
                        beforePage.setData({
                          img_l: res.tempFilePath,
                          nickName: that.data.account,
                          hasloged: that.data.hasloged
                        });
                        // console.log(beforePage.data.img_l);
                        beforePage.changeData(); //触发父页面中的方法

                      }
                    }
                  }
                })
              }
            }
          });
          wx.navigateBack({
            delta: 1
          });
        }
      }
    })
  }
})
