"use strict";define("postSelect",["components"],function(t){var n={};n.pids=[];n.init=function(s){n.pids.length=0;t.get("topic").on("click","[data-pid]",function(){i($(this),s)});e()};function i(t,i){var s=t.attr("data-pid");if(parseInt(t.attr("data-index"),10)===0){return}if(s){var e=n.pids.indexOf(s);if(e===-1){n.pids.push(s);t.toggleClass("bg-success",true)}else{n.pids.splice(e,1);t.toggleClass("bg-success",false)}if(n.pids.length){n.pids.sort(function(t,n){return t-n})}i()}}function s(){return false}function e(){t.get("post").on("click","button,a",s)}n.enableClicksOnPosts=function(){t.get("post").off("click","button,a",s)};return n});
//# sourceMappingURL=public/src/modules/postSelect.js.map