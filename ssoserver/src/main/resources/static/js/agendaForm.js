$(document).ready(function () {
    var updateRenewalRangeDisplay = function(){
        var renewalRange = $('#renewalRange');
        var typeValue = $('#type').val();
        var renewalYearFrom = $('#renewalYearFrom');
        var renewalYearTo = $('#renewalYearTo');
        if (typeValue === "RENEW_COM"){
            renewalRange.show();
            renewalYearFrom.prop('required', true);
            renewalYearTo.prop('required', true);
        } else {
            renewalRange.hide();
            renewalYearFrom.prop('required', false);
            renewalYearTo.prop('required', false);
        }
    };
    $('#type').change(updateRenewalRangeDisplay);
    updateRenewalRangeDisplay();
    $('#agendaForm').submit(function(e){
        var typeValue = $('#type').val();
        var renewalYearFrom = $('#renewalYearFrom').val();
        var renewalYearTo = $('#renewalYearTo').val();
        if (typeValue === "RENEW_COM"){
            if (parseInt(renewalYearTo) < parseInt(renewalYearFrom)){
                e.preventDefault();
                $('#wrongYearRange').show();
            }
        }
    });
});