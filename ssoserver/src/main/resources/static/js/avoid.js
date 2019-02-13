$(document).ready(function() {
    var clickBehavior = function(e){
        var btn = $(this);
        e.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var header = {};
        header[csrfHeader] = token;
        console.log(btn.attr("data-renewal-id"));
        console.log(btn.attr("data-reason-type"));
        $.ajax({
            type: 'POST',
            url: btn.attr("data-url"),
            data: {
                renewalId : btn.attr("data-renewal-id"),
                reasonType : btn.attr("data-reason-type")
            },
            headers: header,
            success: function (ret) {
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
                alert(jqXHR.status + ":" + textStatus);
            },
            dataType: 'json'
        });
    };
    $('.ias-avoid').each(function(){
        var btn = $(this);
        btn.click(function(e) {
            e.preventDefault();
            var clickObj = $(this);
            $('#ias-avoid-reason1-btn')
                .attr('data-url', clickObj.attr('data-url'))
                .attr('data-renewal-id', clickObj.attr('data-renewal-id'))
                .unbind('click')
                .click(clickBehavior);
            $('#ias-avoid-reason2-btn')
                .attr('data-url', clickObj.attr('data-url'))
                .attr('data-renewal-id', clickObj.attr('data-renewal-id'))
                .unbind('click')
                .click(clickBehavior);
            $('#applyAvoid').modal('show');
        })
    });
    $('.ias-un-avoid').each(function(){
        var btn = $(this);
        btn.click(clickBehavior);
    });
});