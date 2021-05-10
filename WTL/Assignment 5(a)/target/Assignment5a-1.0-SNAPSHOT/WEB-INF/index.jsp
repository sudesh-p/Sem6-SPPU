<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: laukik
  Date: 5/9/21
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Convertor</title>
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

        #error {
            text-decoration: none;
            list-style-type: none;
            color: darkred;
            font-size: 25pt;
        }

        #form {
            border: black 1pt ridge;
            box-shadow: darkgray 6px 4px 3px;
            border-radius: 10pt;
            width: 40%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-content: center;
            align-items: center;
            padding: 2em;
        }

        #label {
            width: 100%;
            font-size: 15pt;
            color: dimgrey;
            font-family: 'Itim', cursive;
            margin: 0.3em 0.5em;
        }

        #inrString {
            width: 100%;
            margin: 0.3em 0.5em;
        }

        #submit {
            font-size: 15pt;
            font-family: 'Itim', cursive;
            width: 100%;
            margin: 0.3em 0.5em;
        }

    </style>
</head>
<body>
    <div id="container">
        <div class="contents" >
            <div class="inner-div">
                <h2 class="heading" > Currency Convertor </h2>
            </div>
        </div>
        <div class="contents" >
            <div class="inner-div">
                <h2> <s:actionerror id="error"/> </h2>
            </div>
        </div>
        <div class="contents" >
            <div class="inner-div">
                <s:form action="Hello" id="form">
                    <s:label value="Enter you amount in INR" id="label" />
                    <s:textfield type="text" name="inrString" id="inrString" />
                    <s:submit type="submit" id="submit"/>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>
