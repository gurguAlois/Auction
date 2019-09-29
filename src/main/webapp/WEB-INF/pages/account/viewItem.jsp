<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin homepage</title>

    <link rel="stylesheet" href="/template/vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/template/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/template/vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="/template/vendors/linericon/style.css">
    <link rel="stylesheet" href="/template/vendors/owl-carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/template/vendors/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="/template/vendors/nice-select/nice-select.css">
    <link rel="stylesheet" href="/template/vendors/nouislider/nouislider.min.css">

    <link rel="stylesheet" href="/template/css/style.css">


    <style>
        .bidForm input {
            width: 76px;
            border: 1px solid #eeeeee;
            border-radius: 3px;
            padding-left: 10px;
        }
    </style>
</head>

<body>

<div class="product_image_area">
    <div class="container">
        <div class="row s_product_inner">
            <div class="col-lg-6">
                <div class="owl-carousel owl-theme s_Product_carousel">
                    <div class="single-prd-item">
                        <img class="img-fluid" src="/template/img/category/s-p1.jpg" alt="">
                    </div>
                </div>
            </div>
            <div class="col-lg-5 offset-lg-1">
                <div class="s_product_text">
                    <h3>${itemForm.name}</h3>
                    <h2>$ ${itemForm.currentPrice}</h2>
                    <ul class="list">
                        <li><a class="active" href="#"><span>Category</span> : ${itemForm.category}</a></li>

                        <li><a href="#"><span>Started at</span> : $${itemForm.startingPrice}</a></li>
                        <li><a href="#"><span>Expires on</span> : ${itemForm.endDate}</a></li>
                    </ul>
                    <p>${itemForm.description}</p>
                    <form:form method="POST" action="/account/item/${itemForm.id}" modelAttribute="bidForm">
                        <div class="bidForm">
                            <label for="qty">My offer:</label>

                            <form:input path="value" type="number" class="input-text qty" />
                         <button class="button primary-btn" type="submit">Bid now</button>
                        </div>
                    </form:form>
                    <h2 style="color: red;">${errorMessage}</h2>
                    <div class="card_area d-flex align-items-center">
                        <a class="icon_btn" href="#"><i class="lnr lnr lnr-diamond"></i></a>
                        <a class="icon_btn" href="#"><i class="lnr lnr lnr-heart"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/template/vendors/jquery/jquery-3.2.1.min.js"></script>
<script src="/template/vendors/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/template/vendors/skrollr.min.js"></script>
<script src="/template/vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="/template/vendors/nice-select/jquery.nice-select.min.js"></script>
<script src="/template/vendors/jquery.ajaxchimp.min.js"></script>
<script src="/template/vendors/mail-script.js"></script>
<script src="/template/main.js"></script>
</body></html>
