// Pages/selectRole/selectRole.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  iAmParents: function() {
    wx.navigateTo({
      url: '/Pages/parents/parents',
    })
  },
  iAmChildren: function() {
    wx.navigateTo({
      url: '/Pages/children/children',
    })
  }
})