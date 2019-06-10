let app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "",
    title: "",
    context: "",
    tempFilePaths: ""
  },
  titleblur: function(e) {
    // console.log(e);
    var title = e.detail.value.trim();
    if (title !== null) {
      this.setData({
        title: title
      });
      console.log(this.data.title);
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
        console.log(tempFilePaths);
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
    this.setData({
      id: Date.parse(new Date())
    })
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/app/AC/add_main_context',
      data: {
        id: that.data.id,
        title: that.data.title,
        context: that.data.context,

      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'cookie': getApp().globalData.cookie
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
        } else if (message == "success") {
          wx.showToast({
            title: "发表成功",
            icon: 'success',
            duration: 2000
          });
          wx.uploadFile({
            url: 'http://localhost:8080/faceToChild/app/file/add_AC_picture',
            filePath: that.data.tempFilePaths[0],
            header: {
              'cookie': getApp().globalData.cookie
            },
            formData: {
              from_AC: that.data.id,
              file_desc: "luntan",
              type: "1"
            },
            name: 'file',
            success: function(picres) {
              console.log(picres);
              var picUploadMessage = JSON.parse(picres.data).msg;
              console.log(picUploadMessage);
              if (picUploadMessage == null) {
                var toastText = "网络异常";
                wx.showToast({
                  title: toastText,
                  icon: 'none',
                  duration: 2000
                })

              } else if (picUploadMessage == "程序内部错误") {
                wx.showToast({
                  title: "上传图片失败",
                  icon: 'none',
                  duration: 2000
                })
              } else if (picUploadMessage == "success") {
                wx.showToast({
                  title: "上传图片成功",
                  icon: 'success',
                  duration: 2000
                });
                wx.navigateBack({
                  delta: 1
                });
              }
            }
          });
        }
      }
    })
  }
})