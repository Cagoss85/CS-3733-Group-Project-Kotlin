// all access driven through BASE. Must end with a SLASH

var base_url = "PLACEHOLDER___CHANGE LATER ONCE API IS DEPLOYED!!!";

var createChoice_url = base_url + "choice";        //POST
var authenticateUser_url = base_url + "user";      //POST
var getChoice_url = base_url + "choice";           //GET
var getAlternative_url = base_url + "alternative"  //GET
var postFeedback_url = base_url + "feedback"       //POST
var getFeedback_url = base_url + "feedback"        //GET
var createApproval_url = base_url + "approval"     //POST
var createDisapproval_url = base_url + "disapproval" //POST
var completeChoice_url = base_url + "complete"     //POST
var getReport_url = base_url + "report"            //GET