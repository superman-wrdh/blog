/*!
 * jQuery uLED
 * http://www.class.pm/projects/jquery
 *
 * Copyright 2012, Class.PM www.class.pm
 * Written by Marius Stanciu - Sergiu <marius@picozu.net>
 * Licensed under the GPL Version 3 license.
 * Version 1.0.0
 *
 */

var uLED = function (conf) {
    var r, led, timer, new_date, 
    font1 = [" 000    0    000   000     0  00000   00  00000  000   000             ", "0   0  00   0   0 0   0   00  0      0        0 0   0 0   0            ", "0   0   0       0     0  0 0  0     0         0 0   0 0   0   0        ", "0   0   0    000   000  0  0  0000  0000     0   000   0000            ", "0   0   0   0         0 00000     0 0   0   0   0   0     0            ", "0   0   0   0     0   0    0  0   0 0   0  0    0   0    0    0        ", " 000   000  00000  000     0   000   000  0      000   00              "],
    font2 = [" 000    0    000  00000    0  00000   00  00000  000   000             ", "0   0  00   0   0    0    00  0      0        0 0   0 0   0            ", "0  00   0       0   0    0 0  0000  0        0  0   0 0   0   0        ", "0 0 0   0     00     0  0  0      0 0000    0    000   0000            ", "00  0   0    0        0 00000     0 0   0   0   0   0     0            ", "0   0   0   0     0   0    0  0   0 0   0   0   0   0    0    0        ", " 000   000  00000  000     0   000   000    0    000   00              "],
    font3 = ["00000     0 00000 00000 0   0 00000 00000 00000 00000 00000            ", "0   0     0     0     0 0   0 0     0         0 0   0 0   0   0        ", "0   0     0     0     0 0   0 0     0         0 0   0 0   0            ", "0   0     0 00000 00000 00000 00000 00000     0 00000 00000            ", "0   0     0 0         0     0     0 0   0     0 0   0     0            ", "0   0     0 0         0     0     0 0   0     0 0   0     0   0        ", "00000     0 00000 00000     0 00000 00000     0 00000 00000            "],
    h_w = 5, m_d = 0, m_h = 0, m_m = 0, m_s = 0, time_rem, razd, dig = [], d, i, start, rand_num, num;
    var type = conf.type == undefined ? "time" : conf.type, format, color = "#000", bgcolor = "#000", rounded = conf.rounded == undefined ? 1 : conf.rounded, pix_between = 1, hourformat = conf.hourformat == undefined ? 24 : conf.hourformat, n_length = conf.length == undefined ? 8 : conf.length;
    if (conf.size < 30) {
        h_w = conf.size;
    }
    else {
        h_w = 12;
    }
    if (conf.color == "#eee") {
        color = conf.color;
        led = font1;
        format = "ddd:hh:mm:ss";
    }
    else if (conf.color == "#af0") {
        color = conf.color;
        led = font1;
    }
    else if (conf.color == "#f0a") {
        color = conf.color;
        led = font2;
        format = "hh:mm";
    }
    else if (conf.color == "#fa0") {
        color = conf.color;
        led = font3;
        format = "hh:mm:ss";
    }
    if (conf.bgcolor == "#000") {
        bgcolor = conf.bgcolor;
    }
    else if (conf.bgcolor == "#101a10") {
        bgcolor = conf.bgcolor;
    }
    else if (conf.bgcolor == "#222") {
        bgcolor = conf.bgcolor;
    }
    function mtimer(timer, m) {
        var n_t = timer.split(":");
        for (var i = 0; i < n_t.length; i++) {
            n_t[i] = parseInt(n_t[i], 10);
        }
        n_t[0] = new Date().getFullYear() + 1;
        return n_t;
    }
    start = new Date();
    if (type == "random") {
        r = Raphael(conf.id, n_length * 6 * (h_w + pix_between) - (h_w + 2 * pix_between), 7 * (h_w + pix_between) - pix_between);
        for (i = 0; i < n_length * 6; i++) {
            dig[i] = [];
            for (var y = 0; y < 7; y++) {
                dig[i][y] = r.rect(i * (h_w + pix_between), y * (h_w + pix_between), h_w, h_w, rounded).attr({
                    "fill": bgcolor,
                    "stroke": null
                })
            }
        }
        updateTime();
    }
    else {
        r = Raphael(conf.id, format.length * 6 * (h_w + pix_between) - (h_w + 2 * pix_between), 7 * (h_w + pix_between) - pix_between)
    }
    if (type == "countdown") {
        n_t = mtimer(start.getFullYear() + 1 + ":1:1:0:0:00");
        new_date = new Date(n_t[0], n_t[1] - 1, n_t[2], n_t[3], n_t[4]);
        for (i = 0; i < 12 * 6; i++) {
            dig[i] = [];
            for (var y = 0; y < 7; y++) {
                dig[i][y] = r.rect(i * (h_w + pix_between), y * (h_w + pix_between), h_w, h_w, rounded).attr({
                    "fill": bgcolor,
                    "stroke": null
                })
            }
        }
        updateTime();
    }
    if (type == "time") {
        for (i = 0; i < ((format == "mm:ss" || format == "hh:mm") ? 5 : ((format == "hh" || format == "mm" || format == "ss") ? 2 : 8)) * 6; i++) {
            dig[i] = [];
            for (var y = 0; y < 7; y++) {
                dig[i][y] = r.rect(i * (h_w + pix_between), y * (h_w + pix_between), h_w, h_w, rounded).attr({
                    "fill": bgcolor,
                    "stroke": null
                })
            }
        }
        updateTime();
    }
    function updateTime() {
        d = new Date();
        type == "countdown" ? updateLed1(d) : "";
        type == "time" ? updateLed2(d) : "";
        if (type == "random") {
            updateLed0();
            setTimeout(updateTime, 5000);
        }
        else {
            setTimeout(updateTime, 1000);
        }
    }
    function updateLed0() {
        rand_num = ("0,9999999").split(",");
        num = "" + parseInt(parseInt(rand_num[0]) + Math.random() * (parseInt(rand_num[1]) - parseInt(rand_num[0])));
        while (String(num).length < n_length) {
            num = "0" + num;
        }
        dig_to_led(num);
    }
    function updateLed1(d) {
        time_rem = parseInt((new_date.getTime() - d.getTime()) / 1000) + 1;
        m_d = parseInt(time_rem / 86400);
        m_h = parseInt((time_rem - m_d * 86400) / 3600);
        m_m = parseInt((time_rem - m_d * 86400 - m_h * 3600) / 60);
        m_s = parseInt(time_rem - m_d * 86400 - m_h * 3600 - m_m * 60);
        num = (m_d < 10 ? "00" : (m_d > 100 ? "" : "0")) + m_d + ":" + (m_h < 10 ? "0" : "") + m_h + ":" + (m_m < 10 ? "0" : "") + m_m + ":" + (m_s < 10 ? "0" : "") + m_s;
        if ((new_date.getTime() - d.getTime()) >= 0) {
            dig_to_led(num);
        }
        else {
            dig_to_led("000:00:00:00");
        }
    }
    var flash_dot = true;

    function updateLed2(d) {
        m_h = parseInt(d.getHours());
        m_h = (hourformat == 12 ? (m_h > 12 ? m_h - 12 : m_h) : m_h);
        m_m = parseInt(d.getMinutes());
        m_s = parseInt(d.getSeconds());
        if (format == "mm:ss") {
            num = (m_m < 10 ? "0" : "") + m_m + ":" + (m_s < 10 ? "0" : "") + m_s;
        }
        else if (format == "hh:mm") {
            if (flash_dot) {
                num = (m_h < 10 ? "0" : "") + m_h + ":" + (m_m < 10 ? "0" : "") + m_m;
            }
            else {
                num = (m_h < 10 ? "0" : "") + m_h + " " + (m_m < 10 ? "0" : "") + m_m;
            }
            flash_dot = !flash_dot;
        }
        else if (format == "hh") {
            num = (m_h < 10 ? "0" : "") + m_h;
        }
        else if (format == "mm") {
            num = (m_m < 10 ? "0" : "") + m_m;
        }
        else if (format == "ss") {
            num = (m_s < 10 ? "0" : "") + m_s;
        }
        else {
            num = (m_h < 10 ? "0" : "") + m_h + ":" + (m_m < 10 ? "0" : "") + m_m + ":" + (m_s < 10 ? "0" : "") + m_s
        }
        dig_to_led(num);
    }
    function dig_to_led(num) {
        start = new Date();
        razd = 0;
        for (var l = 0; l < num.length; l++) {
            num.charAt(l) == ":" ? razd = 10 : (num.charAt(l) == " " ? razd = 11 : razd = num.charAt(l));
            for (i = 0; i < 6; i++) {
                for (var y = 0; y < 7; y++) {
                    if (led[y].charAt(razd * 6 + i) == "0" && dig[l * 6 + i][y].attrs.fill == bgcolor) {
                        dig[l * 6 + i][y].animate({
                            "fill": color
                        }, 300);
                    }
                    else if (led[y].charAt(razd * 6 + i) == " " && dig[l * 6 + i][y].attrs.fill != bgcolor) {
                        dig[l * 6 + i][y].animate({
                            "fill": bgcolor
                        }, 300);
                    }
                }
            }
        }
    }
};