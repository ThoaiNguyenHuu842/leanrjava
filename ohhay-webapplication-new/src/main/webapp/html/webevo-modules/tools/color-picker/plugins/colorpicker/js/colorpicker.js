! function(e) {
    var o = function() {
        var o = 65,
            t = '<div class="colorpicker"><div class="colorpicker_color"><div><div></div></div></div><div class="colorpicker_hue"><div></div></div><div class="colorpicker_new_color"></div><div class="colorpicker_hex"><label>#</label><input type="text" maxlength="6" size="6" /></div><div class="colorpicker_rgb_r colorpicker_field"><label>R:</label><input type="text" maxlength="3" size="3" /><span></span></div><div class="colorpicker_rgb_g colorpicker_field"><label>G:</label><input type="text" maxlength="3" size="3" /><span></span></div><div class="colorpicker_rgb_b colorpicker_field"><label>B:</label><input type="text" maxlength="3" size="3" /><span></span></div><div class="colorpicker_hsb_h colorpicker_field"><label>H:</label><input type="text" maxlength="3" size="3" /><span></span></div><div class="colorpicker_hsb_s colorpicker_field"><label>S:</label><input type="text" maxlength="3" size="3" /><span></span></div><div class="colorpicker_hsb_b colorpicker_field"><label>B:</label><input type="text" maxlength="3" size="3" /><span></span></div></div>',
            r = {
                eventName: "click",
                onShow: function() {},
                onBeforeShow: function() {},
                onHide: function() {},
                onChange: function() {},
                onSubmit: function() {},
                color: "ff0000",
                livePreview: !0,
                flat: !1
            },
            i = function(o, t) {
                var r = W(o);
                e(t).data("colorpicker").fields.eq(1).val(r.r).end().eq(2).val(r.g).end().eq(3).val(r.b).end()
            },
            a = function(o, t) {
                e(t).data("colorpicker").fields.eq(4).val(o.h).end().eq(5).val(o.s).end().eq(6).val(o.b).end()
            },
            n = function(o, t) {
                e(t).data("colorpicker").fields.eq(0).val(L(o)).end()
            },
            c = function(o, t) {
                e(t).data("colorpicker").selector.css("backgroundColor", "#" + L({
                    h: o.h,
                    s: 100,
                    b: 100
                })), e(t).data("colorpicker").selectorIndic.css({
                    left: parseInt(150 * o.s / 100, 10),
                    top: parseInt(150 * (100 - o.b) / 100, 10)
                })
            },
            d = function(o, t) {
                e(t).data("colorpicker").hue.css("top", parseInt(150 - 150 * o.h / 360, 10))
            },
            l = function(o, t) {
                e(t).data("colorpicker").currentColor.css("backgroundColor", "#" + L(o))
            },
            s = function(o, t) {
                e(t).data("colorpicker").newColor.css("backgroundColor", "#" + L(o))
            },
            p = function(t) {
                var r = t.charCode || t.keyCode || -1;
                if (r > o && 90 >= r || 32 == r) return !1;
                var i = e(this).parent().parent();
                i.data("colorpicker").livePreview === !0 && u.apply(this)
            },
            u = function(o) {
                var t, r = e(this).parent().parent();
                r.data("colorpicker").color = t = this.parentNode.className.indexOf("_hex") > 0 ? T(D(this.value)) : this.parentNode.className.indexOf("_hsb") > 0 ? z({
                    h: parseInt(r.data("colorpicker").fields.eq(4).val(), 10),
                    s: parseInt(r.data("colorpicker").fields.eq(5).val(), 10),
                    b: parseInt(r.data("colorpicker").fields.eq(6).val(), 10)
                }) : j(Y({
                    r: parseInt(r.data("colorpicker").fields.eq(1).val(), 10),
                    g: parseInt(r.data("colorpicker").fields.eq(2).val(), 10),
                    b: parseInt(r.data("colorpicker").fields.eq(3).val(), 10)
                })), o && (i(t, r.get(0)), n(t, r.get(0)), a(t, r.get(0))), c(t, r.get(0)), d(t, r.get(0)), s(t, r.get(0)), r.data("colorpicker").onChange.apply(r, [t, L(t), W(t)])
            },
            h = function() {
                var o = e(this).parent().parent();
                o.data("colorpicker").fields.parent().removeClass("colorpicker_focus")
            },
            f = function() {
                o = this.parentNode.className.indexOf("_hex") > 0 ? 70 : 65, e(this).parent().parent().data("colorpicker").fields.parent().removeClass("colorpicker_focus"), e(this).parent().addClass("colorpicker_focus")
            },
            v = function(o) {
                var t = e(this).parent().find("input").focus(),
                    r = {
                        el: e(this).parent().addClass("colorpicker_slider"),
                        max: this.parentNode.className.indexOf("_hsb_h") > 0 ? 360 : this.parentNode.className.indexOf("_hsb") > 0 ? 100 : 255,
                        y: o.pageY,
                        field: t,
                        val: parseInt(t.val(), 10),
                        preview: e(this).parent().parent().data("colorpicker").livePreview
                    };
                e(document).bind("mouseup", r, g), e(document).bind("mousemove", r, m)
            },
            m = function(e) {
                return e.data.field.val(Math.max(0, Math.min(e.data.max, parseInt(e.data.val + e.pageY - e.data.y, 10)))), e.data.preview && u.apply(e.data.field.get(0), [!0]), !1
            },
            g = function(o) {
                return u.apply(o.data.field.get(0), [!0]), o.data.el.removeClass("colorpicker_slider").find("input").focus(), e(document).unbind("mouseup", g), e(document).unbind("mousemove", m), !1
            },
            k = function(o) {
                o.preventDefault();
                var t = o;
                "undefined" != typeof event && event.touches && (t = event.touches[0]);
                var r = {
                    cal: e(this).parent(),
                    y: e(this).offset().top
                };
                return r.preview = r.cal.data("colorpicker").livePreview, e(document).bind("mouseup touchend", r, x), e(document).bind("mousemove touchmove", r, _), b(t, r, r.preview), !1
            },
            b = function(e, o, t) {
                u.apply(o.cal.data("colorpicker").fields.eq(4).val(parseInt(360 * (150 - Math.max(0, Math.min(150, e.pageY - o.y))) / 150, 10)).get(0), [t])
            },
            _ = function(e) {
                var o = e;
                return "undefined" != typeof event && event.touches && (o = event.touches[0]), b(o, e.data, e.data.preview), !1
            },
            x = function(o) {
                return i(o.data.cal.data("colorpicker").color, o.data.cal.get(0)), n(o.data.cal.data("colorpicker").color, o.data.cal.get(0)), e(document).unbind("mouseup touchend", x), e(document).unbind("mousemove touchmove", _), !1
            },
            w = function(o) {
                o.preventDefault();
                var t = {
                    cal: e(this).parent(),
                    pos: e(this).offset()
                };
                t.preview = t.cal.data("colorpicker").livePreview, e(document).bind("mouseup touchend", t, M), e(document).bind("mousemove touchmove", t, y), e(".colorpicker_color", t.cal).one("click", t, y), o.data = t, y(o)
            },
            y = function(e) {
                var o = e;
                return "undefined" != typeof event && event.touches && (o = event.touches[0]), u.apply(e.data.cal.data("colorpicker").fields.eq(6).val(parseInt(100 * (150 - Math.max(0, Math.min(150, o.pageY - e.data.pos.top))) / 150, 10)).end().eq(5).val(parseInt(100 * Math.max(0, Math.min(150, o.pageX - e.data.pos.left)) / 150, 10)).get(0), [e.data.preview]), !1
            },
            M = function(o) {
                return i(o.data.cal.data("colorpicker").color, o.data.cal.get(0)), n(o.data.cal.data("colorpicker").color, o.data.cal.get(0)), e(document).unbind("mouseup touchend", M), e(document).unbind("mousemove touchmove", y), !1
            },
            C = function() {
                e(this).addClass("colorpicker_focus")
            },
            I = function() {
                e(this).removeClass("colorpicker_focus")
            },
            q = function() {
                var o = e(this).parent(),
                    t = o.data("colorpicker").color;
                o.data("colorpicker").origColor = t, l(t, o.get(0)), o.data("colorpicker").onSubmit(t, L(t), W(t), o.data("colorpicker").el)
            },
            P = function(o) {
                var t = e("body"),
                    r = t.data("colorpickerId"),
                    i = e(this).data("colorpickerId");
                r && r === i ? (e(document).trigger("mousedown"), t.data("colorpickerId", null)) : (N.call(this, o), t.data("colorpickerId", i))
            },
            N = function() {
                var o = e("#" + e(this).data("colorpickerId"));
                o.data("colorpicker").onBeforeShow.apply(this, [o.get(0)]);
                var t = e(this).offset(),
                    r = O(),
                    i = t.top + this.offsetHeight,
                    a = t.left;
                return i + 176 > r.t + r.h && (i -= this.offsetHeight + 176), a + 356 > r.l + r.w && (a = Math.max(0, a - 356)), o.css({
                    left: a + "px",
                    top: i + "px"
                }), 0 != o.data("colorpicker").onShow.apply(this, [o.get(0)]) && o.show(), e(document).bind("mousedown", {
                    cal: o
                }, S), !1
            },
            S = function(o) {
                H(o.data.cal.get(0), o.target, o.data.cal.get(0)) || (0 != o.data.cal.data("colorpicker").onHide.apply(this, [o.data.cal.get(0)]) && o.data.cal.hide(), H(e(o.data.cal[0]).data("colorpicker").el, o.target) || e("body").data("colorpickerId", null), e(document).unbind("mousedown", S))
            },
            H = function(e, o, t) {
                if (e == o) return !0;
                if (e.contains) return e.contains(o);
                if (e.compareDocumentPosition) return !!(16 & e.compareDocumentPosition(o));
                for (var r = o.parentNode; r && r != t;) {
                    if (r == e) return !0;
                    r = r.parentNode
                }
                return !1
            },
            O = function() {
                var e = "CSS1Compat" == document.compatMode;
                return {
                    l: window.pageXOffset || (e ? document.documentElement.scrollLeft : document.body.scrollLeft),
                    t: window.pageYOffset || (e ? document.documentElement.scrollTop : document.body.scrollTop),
                    w: window.innerWidth || (e ? document.documentElement.clientWidth : document.body.clientWidth),
                    h: window.innerHeight || (e ? document.documentElement.clientHeight : document.body.clientHeight)
                }
            },
            z = function(e) {
                return {
                    h: Math.round(Math.min(360, Math.max(0, e.h))),
                    s: Math.round(Math.min(100, Math.max(0, e.s))),
                    b: Math.round(Math.min(100, Math.max(0, e.b)))
                }
            },
            Y = function(e) {
                return {
                    r: Math.min(255, Math.max(0, e.r)),
                    g: Math.min(255, Math.max(0, e.g)),
                    b: Math.min(255, Math.max(0, e.b))
                }
            },
            D = function(e) {
                var o = 6 - e.length;
                if (o > 0) {
                    for (var t = [], r = 0; o > r; r++) t.push("0");
                    t.push(e), e = t.join("")
                }
                return e
            },
            E = function(e) {
                var e = parseInt(e.indexOf("#") > -1 ? e.substring(1) : e, 16);
                return {
                    r: e >> 16,
                    g: (65280 & e) >> 8,
                    b: 255 & e
                }
            },
            T = function(e) {
                return j(E(e))
            },
            j = function(e) {
                var o = {
                        h: 0,
                        s: 0,
                        b: 0
                    },
                    t = Math.min(e.r, e.g, e.b),
                    r = Math.max(e.r, e.g, e.b),
                    i = r - t;
                return o.b = r, o.s = 0 != r ? 255 * i / r : 0, o.h = 0 != o.s ? e.r == r ? (e.g - e.b) / i : e.g == r ? 2 + (e.b - e.r) / i : 4 + (e.r - e.g) / i : -1, o.h *= 60, o.h < 0 && (o.h += 360), o.s *= 100 / 255, o.b *= 100 / 255, o.h = Math.round(o.h), o.s = Math.round(o.s), o.b = Math.round(o.b), o
            },
            W = function(e) {
                var o = {},
                    t = Math.round(e.h),
                    r = Math.round(255 * e.s / 100),
                    i = Math.round(255 * e.b / 100);
                if (0 == r) o.r = o.g = o.b = i;
                else {
                    var a = i,
                        n = (255 - r) * i / 255,
                        c = (a - n) * (t % 60) / 60;
                    360 == t && (t = 0), 60 > t ? (o.r = a, o.b = n, o.g = n + c) : 120 > t ? (o.g = a, o.b = n, o.r = a - c) : 180 > t ? (o.g = a, o.r = n, o.b = n + c) : 240 > t ? (o.b = a, o.r = n, o.g = a - c) : 300 > t ? (o.b = a, o.g = n, o.r = n + c) : 360 > t ? (o.r = a, o.g = n, o.b = a - c) : (o.r = 0, o.g = 0, o.b = 0)
                }
                return {
                    r: Math.round(o.r),
                    g: Math.round(o.g),
                    b: Math.round(o.b)
                }
            },
            B = function(o) {
                var t = [o.r.toString(16), o.g.toString(16), o.b.toString(16)];
                return e.each(t, function(e, o) {
                    1 == o.length && (t[e] = "0" + o)
                }), t.join("")
            },
            L = function(e) {
                return B(W(e))
            },
            X = function() {
                var o = e(this).parent(),
                    t = o.data("colorpicker").origColor;
                o.data("colorpicker").color = t, i(t, o.get(0)), n(t, o.get(0)), a(t, o.get(0)), c(t, o.get(0)), d(t, o.get(0)), s(t, o.get(0))
            };
        return {
            init: function(o) {
                if (o = e.extend({}, r, o || {}), "string" == typeof o.color) o.color = T(o.color);
                else if (void 0 != o.color.r && void 0 != o.color.g && void 0 != o.color.b) o.color = j(o.color);
                else {
                    if (void 0 == o.color.h || void 0 == o.color.s || void 0 == o.color.b) return this;
                    o.color = z(o.color)
                }
                return this.each(function() {
                    if (!e(this).data("colorpickerId")) {
                        var r = e.extend({}, o);
                        r.origColor = o.color;
                        var m = "colorpicker_" + (e(this).attr("id") || parseInt(1e3 * Math.random()));
                        e(this).data("colorpickerId", m);
                        var g = e(t).attr("id", m);
                        r.flat ? g.appendTo(this).show() : g.appendTo(document.body), r.fields = g.find("input").bind("keyup", p).bind("change", u).bind("blur", h).bind("focus", f), g.find("span").bind("mousedown touchstart", v).end().find(">div.colorpicker_current_color").bind("click", X), r.selector = g.find("div.colorpicker_color").bind("touchstart mousedown", w), r.selectorIndic = r.selector.find("div div"), r.el = this, r.hue = g.find("div.colorpicker_hue div"), g.find("div.colorpicker_hue").bind("mousedown touchstart", k), r.newColor = g.find("div.colorpicker_new_color"), r.currentColor = g.find("div.colorpicker_current_color"), g.data("colorpicker", r), g.find("div.colorpicker_submit").bind("mouseenter touchstart", C).bind("mouseleave touchend", I).bind("click", q), i(r.color, g.get(0)), a(r.color, g.get(0)), n(r.color, g.get(0)), d(r.color, g.get(0)), c(r.color, g.get(0)), l(r.color, g.get(0)), s(r.color, g.get(0)), r.flat ? g.css({
                            position: "relative",
                            display: "block"
                        }) : e(this).bind(r.eventName, P)
                    }
                })
            },
            showPicker: function() {
                return this.each(function() {
                    e(this).data("colorpickerId") && N.apply(this)
                })
            },
            hidePicker: function() {
                return this.each(function() {
                    e(this).data("colorpickerId") && e("#" + e(this).data("colorpickerId")).hide()
                })
            },
            setColor: function(o) {
                if ("string" == typeof o) o = T(o);
                else if (void 0 != o.r && void 0 != o.g && void 0 != o.b) o = j(o);
                else {
                    if (void 0 == o.h || void 0 == o.s || void 0 == o.b) return this;
                    o = z(o)
                }
                return this.each(function() {
                    if (e(this).data("colorpickerId")) {
                        var t = e("#" + e(this).data("colorpickerId"));
                        t.data("colorpicker").color = o, t.data("colorpicker").origColor = o, i(o, t.get(0)), a(o, t.get(0)), n(o, t.get(0)), d(o, t.get(0)), c(o, t.get(0)), l(o, t.get(0)), s(o, t.get(0))
                    }
                })
            }
        }
    }();
    e.fn.extend({
        ColorPicker: o.init,
        ColorPickerHide: o.hidePicker,
        ColorPickerShow: o.showPicker,
        ColorPickerSetColor: o.setColor
    })
}(jQuery);