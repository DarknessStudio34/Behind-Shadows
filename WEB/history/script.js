var step = 0;
$(document).ready(function(){
            $.get("roteiros/step0.txt", function(data) {
                $("#texto-container").text(data);
            });
});
function getStep(stepG){
    step += stepG;
    switch(step){
        case 1:
            $(document).ready(function(){
                $.get("roteiros/step1.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 2:
            $(document).ready(function(){
                $.get("roteiros/step2.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 3:
            $(document).ready(function(){
                $.get("roteiros/step3.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 4:
            $(document).ready(function(){
                $.get("roteiros/step4.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 5:
            $(document).ready(function(){
                $.get("roteiros/step5.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 6:
            $(document).ready(function(){
                $.get("roteiros/step6.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 7:
            $(document).ready(function(){
                $.get("roteiros/step7.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 8:
            $(document).ready(function(){
                $.get("roteiros/step8.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
    }
}
