(function() {
	privateBrowerChecker = {
		detectPrivateMode : function(callback) {
			var is_private, browser;
			if (window.webkitRequestFileSystem) {
				browser = 'webkit';
				window.webkitRequestFileSystem(window.TEMPORARY, 100, function() {
					is_private = false;
				}, function(e) {
					console.log(e);
					is_private = true;
				});
			} else if (window.indexedDB && /Firefox/.test(window.navigator.userAgent)) {
				browser = 'firefox';
				var db;
				try {
					db = window.indexedDB.open('test');
				} catch (e) {
					is_private = true;
				}

				if (typeof is_private === 'undefined') {
					this.retry(function isDone() {
						return db.readyState === 'done' ? true : false;
					}, function next(is_timeout) {
						if (!is_timeout) {
							is_private = db.result ? false : true;
						}
					});
				}
			} else if (this.isIE10OrLater(window.navigator.userAgent)) {
				browser = 'ie';
				is_private = false;
				try {
					if (!window.indexedDB) {
						is_private = true;
					}
				} catch (e) {
					is_private = true;
				}
			} else if (window.localStorage && /Safari/.test(window.navigator.userAgent)) {
				browser = 'safari';
				try {
					window.localStorage.setItem('test', 1);
				} catch (e) {
					is_private = true;
				}

				if (typeof is_private === 'undefined') {
					is_private = false;
					window.localStorage.removeItem('test');
				}
			}

			this.retry(function isDone() {
				return typeof is_private !== 'undefined' ? true : false;
			}, function next(is_timeout) {
				callback(is_private, browser);
			});
		},

		retry : function(isDone, next) {
			var current_trial = 0, max_retry = 50, interval = 10, is_timeout = false;
			var id = window.setInterval(function() {
				if (isDone()) {
					window.clearInterval(id);
					next(is_timeout);
				}
				if (current_trial++ > max_retry) {
					window.clearInterval(id);
					is_timeout = true;
					next(is_timeout);
				}
			}, 10);
		},
		// detect IE
		isIE10OrLater : function(user_agent) {
			var ua = user_agent.toLowerCase();
			if (ua.indexOf('msie') === 0 && ua.indexOf('trident') === 0) {
				return false;
			}
			var match = /(?:msie|rv:)\s?([\d\.]+)/.exec(ua);
			if (match && parseInt(match[1], 10) >= 10) {
				return true;
			}
			return false;
		}
	}
}());