const stepHistory = document.getElementById("dayHistory");
var step = 0;
$(document).ready(function(){
    stepHistory.textContent = "------{ Novo Mundo }------";
    $.get("roteiros/step0.txt", function(data) {
        $("#texto-container").text(data);
    });
});
function getStep(stepG){
    step += stepG;
    switch(step){
        case 1:
            stepHistory.textContent = "------{ Mike, dia 1 - Desencontro }------";
            $(document).ready(function(){
                $.get("roteiros/step1.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 2:
            stepHistory.textContent = "------{ Alan, dia 2 - \"Ferrado e sem amigos\" }------";
            $(document).ready(function(){
                $.get("roteiros/step2.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 3:
            stepHistory.textContent = "------{ Mike, dia 2 - Iniciação }------";
            $(document).ready(function(){
                $.get("roteiros/step3.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
        case 4:
            stepHistory.textContent = "------{ Alan, dia 3 - \"Me virando\" }------";
            $(document).ready(function(){
                $.get("roteiros/step4.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 5:
            stepHistory.textContent = "------{ Mike, dia 4 - \"Sombras e escuridão\" }------";
            $(document).ready(function(){
                $.get("roteiros/step5.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 6:
            stepHistory.textContent = "------{ Alan, dia 5 - \"Vivendo ...\" }------"
            $(document).ready(function(){
                $.get("roteiros/step6.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 7:
            stepHistory.textContent = "------{ Mike, dia 6 - \"Até logo ...\" }------"
            $(document).ready(function(){
                $.get("roteiros/step7.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
         case 8:
            stepHistory.textContent = "------{ Alan, dia 7 - \"Perdido nas sombras\" }------"
            $(document).ready(function(){
                $.get("roteiros/step8.txt", function(data) {
                $("#texto-container").text(data);
                });
            });
            break;
    }
}
