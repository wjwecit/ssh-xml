var $au = new AjaxUtil();

var regA = /(?:@\w+)(?=(\)|\>|\<|\'|\"|\s|(&nbsp;))+)/g;
var regA = /(?:@\w+@)/g;
var regB = /@/g;
var sUserAgent = navigator.userAgent;
var isOpera = sUserAgent.indexOf('Opera') > -1;
var isIE = sUserAgent.indexOf('compatible') > -1
		&& sUserAgent.indexOf('MSIE') > -1 && !isOpera;

/*******************************************************************************
 * String Buffer Class, improve the string contact performance.
 */
function StringBuffer() {
	this.__strings__ = new Array;
	if (typeof (StringBuffer._init == 'undefined')) {
		StringBuffer.prototype.append = function(str) {
			this.__strings__.push(str);
		};
		StringBuffer.prototype.toString = function() {
			return this.__strings__.join('');
		};
		StringBuffer._init = true;
	}
}
/*******************************************************************************
 * Iterative lookup children node method.
 */
function $WfindChild(op,sc) {
	var coms = [];
	function find(op, sc) {
		var children = op.childNodes;
		for ( var i = 0; i < children.length; i++) {
			if (typeof children[i] == 'undefined')
				break;
			if (children[i].nodeType == 1) {
				var c = children[i];
				if (sc.toLowerCase().indexOf(c.nodeName.toLowerCase())>-1) {
					coms.push(c);
				} else {
					find(c, sc);
				}
			}
		}
	}
	find(op,sc);
	return coms;
}
/*******************************************************************************
 * Iterative lookup parent node method.
 */
function $WfindParent(child,sParent){
	if(!child)return null;	
	var p=child.parentNode;
	if(!sParent&&p){return p;}
	if(!p||p.nodeName.toLowerCase()=='body')return null;
	if(p.nodeName.toLowerCase()==sParent.toLowerCase()){
		return p;
	}else{
		return $WfindParent(p,sParent);
	}
}
/*******************************************************************************
 * remove the specified parent node from DOM tree.
 */
function $WRemoveParent(e,sp){
	var p=$WfindParent(e,sp);	
	if(p){
		var pp=$WfindParent(p);
		if(pp){
			pp.removeChild(p);
		}
	}
}
function formParam(ps){
	var vps=[];
	if(!ps)return '';
	var i=0,j=0;
	if(typeof ps[i]=='undefined'||typeof ps[i].name=='undefined'||typeof ps[i].value=='undefined')return '';
	vps[j]=ps[i].name+'='+ps[i].value;
	for(i=1,j=1;i<ps.length;i++){
		if(!ps[i].name||!ps[i].value)continue;
		vps[j++]='&'+ps[i].name+'='+ps[i].value;
	}
	return vps.join('');
}
function $WQuery(frm,idtable){
    frm.onsubmit=function(e){
    	e = e || window.event;
    	if(isIE){
    		e.returnValue = false; //IE
	    }else{
	    	e.preventDefault();//Chrome,oprea,firefox
	    }
    };
	var action=frm.action+'?'+formParam($WfindChild(frm,'input,select'));
	$au.post(action, function(txt) {
		var data = eval('(' + txt + ')');
		data = data.acs;
		rend2(data, document.getElementById(idtable));
	});
}
function AjaxUtil() {
	function createXMLHttpRequest() {
		var xmlhttp = null;
		if (window.XMLHttpRequest) {// for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// for IE6, IE5
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		}
		return xmlhttp;
	}
	if (typeof AjaxUtil._init == 'undefined') {
		AjaxUtil.prototype.post = function(url, callback) {
			var oRequest = createXMLHttpRequest();
			if (!oRequest) {
				return;
			}
			oRequest.open('post', encodeURI(encodeURI(url)), true);
			oRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=UTF-8');
			oRequest.onreadystatechange = function() {
				if (oRequest.readyState == 4) {
					if (oRequest.status == 200) {
						try {
							callback(oRequest.responseText);
						} catch (e) {
							alert(e);
						} finally {
							oRequest == null;
						}
					}
				}
			};
			oRequest.send(null);
		};
		AjaxUtil._init = true;
	}
}
function DataTable(data, oContainner) {
	this._oContainner = oContainner;
	this._data = data;
	this._firstElementChild = null;
	this._firstElementIndex = null;

	if (typeof DataTable._init == 'undefined') {
		DataTable.prototype.rend2 = function() {
			rend2(this._data, this._oContainner);
		};
		DataTable._init = true;
	}
}
function deleteAllChild(p) {
	if (!p)	return;
	var firstElementChild = null;
	for ( var i = 0; i < p.childNodes.length; i++) {
		var child = p.childNodes[i];
		if (child && child.nodeType == 1) {
			firstElementChild = child;
			break;
		}
	}
	for ( var i = 0; i < p.childNodes.length; i++) {
		p.removeChild(p.childNodes[0]);
	}
	while (p.hasChildNodes()) {
		p.removeChild(p.childNodes[0]);
	}
	p.appendChild(firstElementChild);
	return firstElementChild;
}
function pageN() {
	document.getElementById('s_pages').value++;
	mysubmit();
}
function pageP() {
	document.getElementById('s_pages').value--;
	mysubmit();
}
function setStyle(obj, attr, value) {
	if (arguments.length < 3)
		return;
	if (typeof obj['style'][attr]) {
		obj['style'][attr] = value;
		return;
	}
	if (typeof obj.attributes == 'undefined' || obj.attributes.length < 1
			|| !obj.attributes['style'] || !obj.attributes['style'].nodeValue) {
		obj.style[attr] = value;
		return;
	}
	var aStyle = obj.attributes['style'].nodeValue.split(';');
	for ( var i = 0; i < aStyle.length; i++) {
		var reg = new RegExp(attr, 'gi');
		if (reg.test(aStyle[i])) {
			aStyle[i] = attr + ':' + value;
			break;
		}
	}
	obj.attributes['style'].nodeValue = aStyle.join(';');
}

function rend2(data, oContainner) {
	if (!oContainner)return;
	var firstElementChild = null;
	var children = oContainner.childNodes;
	for ( var i = 0; i < children.length; i++) {
		if (children[i].nodeType == 1) {
			firstElementChild = children[i];
			break;
		}
	}
	while (oContainner.hasChildNodes()) {
		oContainner.removeChild(oContainner.childNodes[0]);
	}	
	oContainner.appendChild(firstElementChild);
	if (!firstElementChild|| !data || !data[0])	return;
	setStyle(firstElementChild, 'display', '');
	if (isIE) {
		var aChildReg = new Array();
		for ( var i = firstElementChild.childNodes.length - 1; i >= 0; i--) {
			if (firstElementChild.childNodes[i].nodeType != 1) {
				firstElementChild.removeChild(firstElementChild.childNodes[i]);
			}
		}
		for ( var i = 0; i < firstElementChild.childNodes.length; i++) {
			aChildReg.push(firstElementChild.childNodes[i].innerHTML
					.match(regA));
		}
		for ( var r = 0; r < data.length; r++) {
			var p = firstElementChild.cloneNode(true);
			for ( var c = 0; c < firstElementChild.childNodes.length; c++) {
				if (!aChildReg[c])
					continue;
				for ( var rs = 0; rs < aChildReg[c].length; rs++) {
					var fld = aChildReg[c][rs].replace(regB, '');
					if (typeof data[r][fld] == 'undefined')
						continue;
					p.childNodes[c].innerHTML = p.childNodes[c].innerHTML
							.replace(aChildReg[c][rs], data[r][fld]);
					if (!p.childNodes[c].innerHTML)
						p.childNodes[c].innerHTML = '&nbsp;';
				}
			}
			oContainner.appendChild(p);
		}
	} else {
		var aregM = new Array(), afield = new Array();
		var aMatch = firstElementChild.innerHTML.match(regA);
		if (!aMatch)
			aMatch = [];
		for ( var a = 0; a < aMatch.length; a++) {
			var fieldName = aMatch[a].replace(regB, '');
			if (typeof data[0][fieldName] != 'undefined') {
				aregM.push(new RegExp('@' + fieldName + '@', 'g'));
				afield.push(fieldName);
			}
		}
		for ( var r = 0; r < data.length; r++) {
			var p = firstElementChild.cloneNode(true);
			for ( var i = 0; i < aregM.length; i++) {
				if (!data[r][afield[i]])
					data[r][afield[i]] = '';
				p.innerHTML = p.innerHTML.replace(aregM[i], data[r][afield[i]]);
			}
			oContainner.appendChild(p);
		}
	}
	setStyle(firstElementChild, 'display', 'none');
}