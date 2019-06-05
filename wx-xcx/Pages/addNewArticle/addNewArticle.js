// Pages/addNewArticle/addNewArticle.js
/* Pages/addNewArticle/addNewArticle.wxss */
// pages/course/addNewArticle/addNewArticle.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: "",
    context: "",
    tempFilePaths: ""
  },
  titleblur: function(e) {
    var title = e.detail.value.trim();
    if (title !== null) {
      this.setData({
        title: title
      });
      console.log(title);
    }
  },
  contextblur: function(e) {
    var context = e.detail.value.trim();
    if (context !== null) {
      this.setData({
        context: context
      });
      console.log(context);
    }
  },
  //选择本地相册中的校卡
  upLoad() {
    wx.chooseImage({
      success: (res) => {
        let tempFilePaths = res.tempFilePaths;
        if (this.data.tempFilePaths == '') {
          this.setData({
            tempFilePaths: tempFilePaths
          })
        } else {
          this.setData({
            tempFilePaths: this.data.tempFilePaths.concat(tempFilePaths)
          })
        }
      }
    })
  },


  //删除预览图片
  delPictrue(e) {
    let idx = e.currentTarget.dataset.index,
      tempFilePaths = this.data.tempFilePaths;
    tempFilePaths.splice(idx, 1);
    this.setData({
      tempFilePaths: tempFilePaths
    })
  },

  submit: function(e) {
    console.log(e);
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/AC/add_main_context',
      data: {
        id: 10,
        title: that.data.title,
        context: that.data.context,
        userID: 10000
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
            title: "发表失败",
            icon: 'none',
            duration: 2000
          })
        } else if(message == "success") {
          wx.showToast({
            title: "发表成功",
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
wx.uploadFile({
  url: '',
  filePath: '',
  name: '',
})