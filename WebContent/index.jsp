<!DOCTYPE html>
<html lang="en">
<head>
<script>(function(e, p){
 	var m = location.href.match(/platform=(win8|win|mac|linux|cros)/);
 	e.id = (m && m[1]) ||
 	(p.indexOf('Windows NT 6.2') > -1 ? 'win8' : p.indexOf('Windows') > -1 ? 'win' : p.indexOf('Mac') > -1 ? 'mac' : p.indexOf('CrOS') > -1 ? 'cros' : 'linux');
 	e.className = e.className.replace(/\bno-js\b/,'js');
 	})(document.documentElement, window.navigator.userAgent)
 	</script>
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>ALSC HelperBot</title>
<header name = "Access-Control-Allow-Origin" value = "*" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/ionicons.min.css" />
<link rel="stylesheet" href="css/style.css" />
<script>
	var carServiceHost = '<%=com.ibm.cto.Configuration.getInstance().CAR_SERVICE_HOST %>';
</script>
</head>
<body>
	<header class="_demo--heading">
		<div class="_demo--container">
			<a class="wordmark" href="index.jsp">
				<span class="wordmark--left">ALSC Smart</span>
				<span class="wordmark--right">Service Desk</span>
			</a>
			
			<!--nav class="heading-nav">
				<a class="heading-nav--item chatbot" href="./">
					Chatbot
				</a>
			</nav-->
		</div>
	</header>
	<div class="_demo--container">
		<article class="_demo--content base--article page" ref="chatbot">
			<div class="_content--dialog">
				<div class="chat-window">
					<div class="chat-box">
						<div class="chat-box--pane">
							<div class="chat-box--item_WATSON chat-box--item chat-box--item_HIDDEN">
								<div class="chat-box--container">
									<div class="img-container chat-box--avatar chat-box--avatar_WATSON">
										<img src="images/icons/avatar-watson.svg" class="chat-box--avatar-img">
									</div>
									<div class="chat-box--message">
										<div class="chat-box--message-vertical">
											<p class="chat-box--message-text base--p"></p>
										</div>
									</div>
								</div>
							</div>
							<div class="chat-box--item_YOU chat-box--item chat-box--item_HIDDEN">
								<div class="chat-box--container">
									<div class="chat-box--message">
										<div class="chat-box--message-vertical">
											<p class="chat-box--message-text base--p"></p>
										</div>
									</div>
									<div class="img-container chat-box--avatar chat-box--avatar_YOU">
										<img src="images/icons/avatar-me.svg" class="chat-box--avatar-img" />
									</div>
								</div>
							</div>
							<!-- adding extra space hack -->
							<div class="loader">
								<div class="loader--fallback"><img src="images/watson.gif" width="50" alt=""></div>
							</div>
						</div>
					</div>
	
					<input type="text" placeholder="Type a response and hit enter" value="" autocomplete="off" class="chat-window--message-input base--text-input ui-input-message" />
					
				</div>
			</div>



			<div class="ui-transcription"></div>
		</article>

	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/socket.io-1.3.7.js"></script>
	<script src="js/watson-speech.js"></script>
  	<script src="js/talk-to-watson.js"></script>
</body>
</html>
