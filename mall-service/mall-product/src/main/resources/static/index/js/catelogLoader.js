$(function () {
    $.getJSON("product/category/index", function (data) {
        const ctGall = data.data;
        $(".header_main_left_a").each(function () {
            var ctgNums = $(this).attr("ctg-data");
            if (ctgNums) {
                var panel = $("<div class='header_main_left_main'></div>");
                var panelol = $("<ol class='header_ol'></ol>");
                var ctgnumArray = ctgNums.split(",");
                $.each(ctgnumArray, function (i, ctg1Id) {
                    var ctg2list = ctGall[ctg1Id];
                    $.each(ctg2list, function (i, ctg2) {
                        var cate2link = $("<a href='#' style= 'color: #111;' class='aaa'>" + ctg2.name + "  ></a>");


                        console.log(cate2link.html());
                        var li = $("<li></li>");
                        var ctg3List = ctg2["catelog3List"];
                        var len = 0;
                        $.each(ctg3List, function (i, ctg3) {
                            var cate3link = $("<a href=\"http://search.gmall.com/list.html?catelog3Id=" + ctg3.id + "\" style=\"color: #999;\">" + ctg3.name + "</a>");
                            li.append(cate3link);
                            len = len + 1 + ctg3.name.length;
                        });
                        if (len >= 46 && len < 92) {
                            li.attr("style", "height: 60px;");
                        } else if (len >= 92) {
                            li.attr("style", "height: 90px;");
                        }
                        panelol.append(cate2link).append(li);

                    });

                });
                panel.append(panelol);
                $(this).after(panel);
                $(this).parent().addClass("header_li2");
                console.log($(".header_main_left").html());
            }
        });
    });
});
