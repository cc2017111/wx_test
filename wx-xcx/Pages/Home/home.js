Page({

  /**
   * 页面的初始数据
   */
  data: {
    child_info: [
      {
        imagePath: "/images/child_1.jpg",
        childAge: 12,
        childSex: "男孩",
        missingDate: "2019年05月13号10时",
        missingPosition: "袁乐，平头，于都县罗坳镇"
      },
      {
        imagePath: "/images/child_2.jpg",
        childAge: 7,
        childSex: "男孩",
        missingDate: "2019年05月15号11时",
        missingPosition: "武汉市中山公园山峡"
      },
      {
        imagePath: "/images/child_3.jpg",
        childAge: 13,
        childSex: "男孩",
        missingDate: "2019年05月15号15时",
        missingPosition: "林观平，平头，于都县罗坳镇"
      },
    ],
    mainSwiperData: [
      {
        imagePath: "/Images/home_main.jpg",
      },
      {
        imagePath: "/Images/home_main.jpg",
      }
    ],
    indicatorDots: false,
    autoplay: false,
    interval: 5000,
    duration: 1000
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  mainLoginBtn: function() {
    wx.navigateTo({
      url: '/Pages/login/login',
    })
  }
})