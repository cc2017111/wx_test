let app = getApp();
var prom = require("../../util/promisify.js");
Page({
  data: {
    showMe: '',
    hasloged: false,
    page_num: "1",
    page_size: '2',
    keyword: '',
    info_list: [],
    info_list_is_not_empty: false,
    info_list_len: 0,
    img_l: '',
    nickName: '',
    offset: 0,
    userInfo: {},
    banners: [{
      url: '/b/a/a?aid=1&type=2',
      img: '/images/home_main.jpg'
    }, {
      url: '/b/a/a?aid=1&type=2',
      img: '/images/home_main1.jpg'
    }, {
      url: '/b/a/a?aid=1&type=2',
      img: '/images/home_main2.jpg'
    }, {
      url: '/b/a/a?aid=1&type=2',
      img: '/images/home_main3.jpg'
    }],
    mine: [{
      url: '/u/f/f?type=favorite',
      img: '/assets/images/gameed.png',
      word: '我的收藏'
    }, {
      url: '/b/i/i?tab=time&from=myc',
      img: '/assets/images/icons/share.png',
      word: '我的评论'
    }, {
      url: '/b/i/i?tab=time&from=cme',
      img: '/assets/images/icons/comment.png',
      word: '评论我的'
    }]
  },
  changeData: function() {
    var options = {
      'id': this.data.id
    };
    // console.log(this.data.img_l);
    this.onLoad();
  },
  addNewArticle: function() {
    wx.navigateTo({
      url: '/Pages/addNewArticle/addNewArticle',
    })
  },

  getACData: function(arr, res, i) {
    var that = this;
    var obj = {};
    obj.id = res.data.data.data[i].id;
    obj.title = res.data.data.data[i].title;
    obj.context = res.data.data.data[i].context;
    obj.userID = res.data.data.data[i].userID;
    prom.wxPromisify(wx.request)({
      url: 'http://localhost:8080/faceToChild/app/file/found_AC_picture',
      header: {
        'cookie': getApp().globalData.cookie,
        'content-type': 'application/json'
      },
      method: "GET",
      data: {
        from_ac: res.data.data.data[i].id
      }
    }).then(function(urlRes) {
      if (urlRes.statusCode === 200) {
        console.log(urlRes);
        var url_temp = urlRes.data.data.url;
        console.log(url_temp);
        prom.wxPromisify(wx.downloadFile)({
          url: 'http://localhost:8080/faceToChild/' + url_temp,
        }).then(function(successRes) {
          if (urlRes.statusCode === 200) {
            obj.img = successRes.tempFilePath;
            console.log(obj.img);
            arr.push(obj);
            that.setData({
              info_list: arr
            });
          }
        })
      }
    })
  },
  searchInput: function(e) {
    var keyword = e.detail.value;
    if (keyword != '') {
      this.setData({
        keyword: keyword
      });
    }
    console.log(keyword);
  },

  showMine: function(e) {
    this.setData({
      showMe: 'show-me',
    })
  },
  touchSP: function(e) {
    this.setData({
      showMe: ''
    })
  },
  login: function(e) {
    wx.navigateTo({
      url: '/Pages/login/login',
    })
  },
  showLogin: function(e) {
    wx.navigateTo({
      url: '/Pages/login/login',
    })
  },
  searchTap: function() {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/app/AC/list',
      header: {
        'cookie': getApp().globalData.cookie,
        'content-type': 'application/json'
      },
      method: "GET",
      data: {
        page_num: that.data.page_num,
        page_size: that.data.page_size,
        keyword: that.data.keyword
      },
      success: function (res) {
        if (res.statusCode === 200) {
          var arr = [];
          var len = res.data.data.data.length;
          if (len > 0) {
            that.setData({
              info_list_is_not_empty: true,
              info_list_len: len
            })
          }
          for (let i = 0; i < len; i++) {
            that.getACData(arr, res, i);
          }
        }
      }
    })

  },
  onLoad() {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/faceToChild/app/AC/list',
      header: {
        'cookie': getApp().globalData.cookie,
        'content-type': 'application/json'
      },
      method: "GET",
      data: {
        page_num: that.data.page_num,
        page_size: that.data.page_size,
        keyword: that.data.keyword
      },
      success: function(res) {
        if (res.statusCode === 200) {
          var arr = [];
          var len = res.data.data.data.length;
          if (len > 0) {
            that.setData({
              info_list_is_not_empty: true,
              info_list_len: len
            })
          }
          for (let i = 0; i < len; i++) {
            that.getACData(arr, res, i);
          }
        }
      }
    })
  }

})