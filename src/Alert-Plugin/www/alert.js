module.exports = {
  alert: function(title, message, buttonLabel, successCallback) {
    cordova.exec(successCallback,
                 null, // No failure callback
                 "Alert",
                 "alert",
                 [title, message, buttonLabel]);
  }
};