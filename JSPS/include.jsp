<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C/DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns = "http://www.w3.org/1999/xhtml">
  <head>
    <title>Using jsp:include</title>
    <style type = "text/css">
      body {
        font-family: tahoma, helvetica, arial, sans-serif;
      }
      table, tr, td {
        border: 3px groove;
        padding: 5px;
        background-color: #dddddd;
      }
      .big { font-family: helvetica, arial, sans-serif;
        font-weight: bold;
      }
    </style>
  </head>
  <body>
    <table>
      <tr>
        <td style="width: 160px; text-align: center">
          <img src = "images/logotiny.png"
            width = "146" height = "92" />
        </td>
        <td>
          <jsp:include page = "banner.html" flush = "true" />
        </td>
      </tr>
        <td style = "width: 160px">
          <jsp:include page = "toc.html" flush = "true" />
        </td>
        <td style = "vertical-align: top">
          <%= new java.util.Date() %>
        </td>
      </tr>
    </table>
  </body>
</html>
