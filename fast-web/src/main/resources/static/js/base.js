/**
 * Created by Starfire on 2016/6/19.
 */
$(function(){
    winResize();
    $(window).resize(function(){
        winResize();
    });

});
/*welcome 占满全屏*/
function winResize(){
    $('#welcomeBg').height($(window).height());
    $('#welcomeBg').width($(window).width());
}