var step = 0;
$(document).ready(function(){
            $.get("roteiros/limpo/step0.txt", function(data) {
                $("#texto-container").text(data);
            });
});
function getStep(){
    step += 1;
    switch(step){
        case 1:
            $(document).ready(function(){
                $.get("roteiros/limpo/step1.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 2:
            $(document).ready(function(){
                $.get("roteiros/limpo/step2.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 3:
            $(document).ready(function(){
                $.get("roteiros/limpo/step3.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
    }
}
