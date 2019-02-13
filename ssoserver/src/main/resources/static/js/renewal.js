;
(function ($) {
    var app = this.app || (this.app = {});
    app.renderExistingPageResult = function (pageResult) {
            $('.existingPageResult').each(function(){
                $(this).remove();
            });
            var shots = $('.existingPageResultPrototype');
            for (var i = 0; i < pageResult.currentPageElement.length; i++) {
               shots.clone().removeClass('existingPageResultPrototype')
                   .addClass('existingPageResult').insertAfter(shots);
            }

            $.each(pageResult.currentPageElement, function(i, e) {
                $('.existingPageResult .communityIds input[type="checkbox"]').eq(i).val(e.id);
                $('.existingPageResult p.communityHeader').eq(i).html(e.legalPersonNum + '-' +  e.dsiNum);
                $('.existingPageResult p.communityNameChinese').eq(i).html(e.nameChinese);
                $('.existingPageResult p.communityNameForeign').eq(i).html(e.nameForeign);
            });
    };
    app.renderPoolPageResult = function (pageResult) {
        $('.poolPageResult').each(function(){
            $(this).remove();
        });
        var shots = $('.poolPageResultPrototype');
        for (var i = 0; i < pageResult.currentPageElement.length; i++) {
            shots.clone().removeClass('poolPageResultPrototype')
                .addClass('poolPageResult').insertAfter(shots);
        }

        $.each(pageResult.currentPageElement, function(i, e) {
            $('.poolPageResult .communityIds input[type="checkbox"]').eq(i).val(e.id);
            $('.poolPageResult p.communityHeader').eq(i).html(e.legalPersonNum + '-' +  e.dsiNum);
            $('.poolPageResult p.communityNameChinese').eq(i).html(e.nameChinese);
            $('.poolPageResult p.communityNameForeign').eq(i).html(e.nameForeign);
        });
    };
    app.updateExistingNavPageResult = function (targetPage, pageResult) {
        localStorage.setItem("existingCurrentPage", targetPage);
        localStorage.setItem("existingTotalPage", pageResult.totalPages);
        if (targetPage >= pageResult.totalPages - 1) {
            $('#existingPageResultNext').css('visibility', 'hidden');
        } else {
            $('#existingPageResultNext').css('visibility', 'visible');
        }
        if (targetPage <= 0) {
            $('#existingPageResultPrev').css('visibility', 'hidden');
        } else {
            $('#existingPageResultPrev').css('visibility', 'visible');
        }
    };
    app.updatePoolNavPageResult = function (targetPage, pageResult) {
        localStorage.setItem("poolCurrentPage", targetPage);
        localStorage.setItem("poolTotalPage", pageResult.totalPages);
        if (targetPage >= pageResult.totalPages - 1) {
            $('#poolPageResultNext').css('visibility', 'hidden');
        } else {
            $('#poolPageResultNext').css('visibility', 'visible');
        }
        if (targetPage <= 0) {
            $('#poolPageResultPrev').css('visibility', 'hidden');
        } else {
            $('#poolPageResultPrev').css('visibility', 'visible');
        }
    };
    app.fetchData = function (targetPage) {
        var url = $("meta[name='app_existing_page_result_url']").attr("content");
        $.ajax({
            type: 'GET',
            url: url + '?page=' + targetPage,
            success: function (ret) {
                app.updateExistingNavPageResult(targetPage, ret);
                app.renderExistingPageResult(ret);
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
    app.fetchPoolData = function (targetPage) {
        var url = $("meta[name='app_pool_page_result_url']").attr("content");
        $.ajax({
            type: 'GET',
            url: url + '?page=' + targetPage,
            success: function (ret) {
                app.updatePoolNavPageResult(targetPage, ret);
                app.renderPoolPageResult(ret);
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
    $(document).ready(function(){
        app.fetchData(0);
        app.fetchPoolData(0);
        $('#existingPageResultNext').click(function (e) {
            e.preventDefault();
            var targetPage = parseInt(localStorage.getItem("existingCurrentPage")) + 1;
            app.fetchData(targetPage);
        });
        $('#existingPageResultPrev').click(function (e) {
            e.preventDefault();
            var targetPage = parseInt(localStorage.getItem("existingCurrentPage")) - 1;
            app.fetchData(targetPage);
        });
        $('#poolPageResultNext').click(function (e) {
            e.preventDefault();
            var targetPage = parseInt(localStorage.getItem("poolCurrentPage")) + 1;
            app.fetchPoolData(targetPage);
        });
        $('#poolPageResultPrev').click(function (e) {
            e.preventDefault();
            var targetPage = parseInt(localStorage.getItem("poolCurrentPage")) - 1;
            app.fetchPoolData(targetPage);
        });
        $('#deleteButton').click(function(e){
            var hrefObj = $(this);
            var token = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            e.preventDefault();
            var header = {};
            header[csrfHeader] = token;
            var communityIds = [];
            $('.existingPageResult input[type="checkbox"]:checked').each(function() {
                communityIds.push($(this).val());
            });
            $.ajax({
                type: 'POST',
                url: hrefObj.attr('href'),
                data: {
                    communityIds: communityIds
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
        });
        $('#addButton').click(function(e){
            var hrefObj = $(this);
            var token = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            e.preventDefault();
            var header = {};
            header[csrfHeader] = token;
            var communityIds = [];
            $('.poolPageResult input[type="checkbox"]:checked').each(function() {
                communityIds.push($(this).val());
            });
            $.ajax({
                type: 'POST',
                url: hrefObj.attr('href'),
                data: {
                    communityIds: communityIds
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
        });
        $('#existingSelectAll').click(function(e) {
            e.preventDefault();
            $('.existingPageResult input[type="checkbox"]').each(function() {
                $(this).prop('checked', true);
            });
        });
        $('#existingDeSelectAll').click(function(e) {
            e.preventDefault();
            $('.existingPageResult input[type="checkbox"]').each(function() {
                $(this).prop('checked', false);
            });
        });

        $('#poolSelectAll').click(function(e) {
            e.preventDefault();
            $('.poolPageResult input[type="checkbox"]').each(function() {
                $(this).prop('checked', true);
            });
        });
        $('#poolDeSelectAll').click(function(e) {
            e.preventDefault();
            $('.poolPageResult input[type="checkbox"]').each(function() {
                $(this).prop('checked', false);
            });
        });
    });
}).call(this, jQuery);