<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: laukik
  Date: 5/9/21
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Converted Value</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Itim&display=swap');
        * {
            margin: 0;
            padding: 0;
        }

        #container {
            width: 100%;
            height: 100%;
            margin-left: 10vw;
            margin-right: 10vw;
        }

        .contents {
            align-self: center;
            width: 80vw;
        }

        .inner-div {
            display: flex;
            margin: 1em;
            padding: 0.5em;
            justify-content: center;
            align-content: center;
        }

        .heading {
            text-decoration: none;
            list-style-type: none;
            color: forestgreen;
            font-size: 25pt;
        }

        h2 {
            font-size: 35pt;
        }

        h3 {
            font-size: 25pt;
            font-family: 'Itim', cursive;
        }


        h4 {
            font-size: 20pt;
            font-family: 'Itim', cursive;
        }

        .form {
            border: black 1pt ridge;
            box-shadow: darkgray 6px 4px 3px;
            border-radius: 10pt;
            width: 8%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-content: center;
            align-items: center;
            padding: 2em;
            margin-left: 1em;
            margin-right: 1em;
        }

        #cards {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-content: center;
        }

    </style>
</head>
<body>

    <div id="container">
        <div class="contents">
            <div class="inner-div">
                <h2 class="heading">Success</h2>
            </div>
        </div>
        <div class="contents">
            <div id="cards">
                <div class="form">
                    <h3>INR</h3>
                    <h4><s:property value="inr" /></h4>
                </div>
                <div class="form">
                    <h3>USD</h3>
                    <h4><s:property value="usd" /></h4>
                </div>
                <div class="form">
                    <h3>EUR</h3>
                    <h4><s:property value="eur" /></h4>
                </div>
                <div class="form">
                    <h3>GBP</h3>
                    <h4><s:property value="gbp" /></h4>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
