
/*
 *
 *   Function to calculate average of general feelings rating
 *
 */

Parse.Cloud.define("averageFeelings", function(request, response) {
  var query = new Parse.Query("ratings");
  query.find({
    success: function(results) {
      var sum = 0;
      for (var i = 0; i < results.length; ++i) {
        sum += results[i].get("feelings");
      }
      response.success(sum / results.length);
    },
    error: function() {
      response.error("Calculating ratings failed");
    }
  });

});
