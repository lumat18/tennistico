package com.gruzini.tennistico.models.forms;

import lombok.Data;

@Data
public class VerificationEmail {
    private String verificationLink;

    public VerificationEmail(String verificationLink) {
        this.verificationLink = verificationLink;
    }
    public String getBody(){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\"\n" +
                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                ">\n" +
                "<head>\n" +
                "    <!--[if gte mso 9]>\n" +
                "    <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "            <o:AllowPNG/>\n" +
                "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "    </xml><![endif]-->\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                "    <meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                "    <!--<![endif]-->\n" +
                "    <title></title>\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <!--<![endif]-->\n" +
                "    <style type=\"text/css\">\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        table,\n" +
                "        td,\n" +
                "        tr {\n" +
                "            vertical-align: top;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            line-height: inherit;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors=true] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <style id=\"media-query\" type=\"text/css\">\n" +
                "        @media (max-width: 660px) {\n" +
                "\n" +
                "            .block-grid,\n" +
                "            .col {\n" +
                "                min-width: 320px !important;\n" +
                "                max-width: 100% !important;\n" +
                "                display: block !important;\n" +
                "            }\n" +
                "\n" +
                "            .block-grid {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .col {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .col > div {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            img.fullwidth,\n" +
                "            img.fullwidthOnMobile {\n" +
                "                max-width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col {\n" +
                "                min-width: 0 !important;\n" +
                "                display: table-cell !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack.two-up .col {\n" +
                "                width: 50% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num4 {\n" +
                "                width: 33% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num8 {\n" +
                "                width: 66% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num4 {\n" +
                "                width: 33% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num3 {\n" +
                "                width: 25% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num6 {\n" +
                "                width: 50% !important;\n" +
                "            }\n" +
                "\n" +
                "            .no-stack .col.num9 {\n" +
                "                width: 75% !important;\n" +
                "            }\n" +
                "\n" +
                "            .video-block {\n" +
                "                max-width: none !important;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0px;\n" +
                "                max-height: 0px;\n" +
                "                max-width: 0px;\n" +
                "                display: none;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide {\n" +
                "                display: block !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #f8f8f9;\">\n" +
                "<table bgcolor=\"#f8f8f9\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                "       style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9; width: 100%;\"\n" +
                "       valign=\"top\" width=\"100%\">\n" +
                "    <tbody>\n" +
                "    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "        <td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                "            <div style=\"background-color:transparent;\">\n" +
                "                <div class=\"block-grid\"\n" +
                "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 640px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #fff;\">\n" +
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#fff;background-image:url('https://images.unsplash.com/photo-1547934045-2942d193cb49?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1955&q=80');background-position:top left;background-repeat:no-repeat\">\n" +
                "\n" +
                "                        <div class=\"col num12\"\n" +
                "                             style=\"min-width: 320px; max-width: 640px; display: table-cell; vertical-align: top; width: 640px;\">\n" +
                "                            <div style=\"width:100% !important;\">\n" +
                "\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\"\n" +
                "                                       role=\"presentation\"\n" +
                "                                       style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
                "                                       valign=\"top\" width=\"100%\">\n" +
                "                                    <tbody>\n" +
                "                                    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "                                        <td class=\"divider_inner\"\n" +
                "                                            style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 45px; padding-right: 0px; padding-bottom: 12px; padding-left: 0px;\"\n" +
                "                                            valign=\"top\">\n" +
                "                                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                   class=\"divider_content\" role=\"presentation\"\n" +
                "                                                   style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid #BBBBBB; width: 100%;\"\n" +
                "                                                   valign=\"top\" width=\"100%\">\n" +
                "                                                <tbody>\n" +
                "                                                <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "                                                    <td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
                "                                                        valign=\"top\"><span></span></td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                                <div align=\"center\" class=\"img-container center fixedwidth\"\n" +
                "                                     style=\"padding-right: 40px;padding-left: 40px;\">\n" +
                "                                   <img align=\"center\" alt=\"I'm an image\" border=\"0\"\n" +
                "                                                     class=\"center fixedwidth\" src=\"https://s7.gifyu.com/images/logo10184d5baf15c4704.png\"\n" +
                "                                                     style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 352px; display: block;\"\n" +
                "                                                     title=\"I'm an image\" width=\"352\"/>\n" +
                "                                </div>\n" +
                "\n" +
                "                                <div style=\"color:#e40f0f;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\n" +
                "                                    <div style=\"line-height: 1.2; font-size: 12px; color: #e40f0f; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "                                        <p style=\"font-size: 30px; line-height: 1.2; text-align: center; word-break: break-word; mso-line-height-alt: 36px; margin: 0;\">\n" +
                "                                            <span style=\"font-size: 30px; color: #ffffff;\"><strong>Verify Your Email Account</strong></span>\n" +
                "                                        </p>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "\n" +
                "\n" +
                "                                <div align=\"center\" class=\"button-container\"\n" +
                "                                     style=\"padding-top:15px;padding-right:10px;padding-bottom:0px;padding-left:10px;\">\n" +
                "\n" +
                "                                    <div style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#1aa19c;border-radius:60px;-webkit-border-radius:60px;-moz-border-radius:60px;width:auto; width:auto;;border-top:1px solid #1aa19c;border-right:1px solid #1aa19c;border-bottom:1px solid #1aa19c;border-left:1px solid #1aa19c;padding-top:15px;padding-bottom:15px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;text-align:center;mso-border-alt:none;word-break:keep-all;\">\n" +
                "                                            <a href="+this.verificationLink+" style=\"padding-left:30px;padding-right:30px;font-size:16px;display:inline-block;\"><span\n" +
                "                                                    style=\"font-size: 16px; margin: 0; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>Confirm Your Email</strong></span></a>\n" +
                "                                    </div>\n" +
                "\n" +
                "                                    <div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:35px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\n" +
                "                                        <div style=\"line-height: 1.5; font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; color: #555555; mso-line-height-alt: 18px;\">\n" +
                "                                            <p style=\"font-size: 15px; line-height: 1.5; text-align: center; word-break: break-word; font-family: inherit; mso-line-height-alt: 23px; margin: 0;\">\n" +
                "                                                <span style=\"color: #ffffff; font-size: 15px;\">Thank you for signing up in Tennistico! There is just one little step left to complete your registration process. Click the link below to confirm your accout.</span>\n" +
                "                                            </p>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"background-color:transparent;\">\n" +
                "                <div class=\"block-grid\"\n" +
                "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 640px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #252f3f;\">\n" +
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#252f3f;\">\n" +
                "\n" +
                "\n" +
                "                        <div class=\"col num12\"\n" +
                "                             style=\"min-width: 320px; max-width: 640px; display: table-cell; vertical-align: top; width: 640px;\">\n" +
                "                            <div style=\"width:100% !important;\">\n" +
                "                                <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n" +
                "                                    <!--<![endif]-->\n" +
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"social_icons\" role=\"presentation\"\n" +
                "                                           style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                           valign=\"top\" width=\"100%\">\n" +
                "                                        <tbody>\n" +
                "                                        <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "                                            <td style=\"word-break: break-word; vertical-align: top; padding-top: 28px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\"\n" +
                "                                                valign=\"top\">\n" +
                "                                                <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                       class=\"social_table\" role=\"presentation\"\n" +
                "                                                       style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-tspace: 0; mso-table-rspace: 0; mso-table-bspace: 0; mso-table-lspace: 0;\"\n" +
                "                                                       valign=\"top\">\n" +
                "                                                    <tbody>\n" +
                "                                                    <tr align=\"center\"\n" +
                "                                                        style=\"vertical-align: top; display: inline-block; text-align: center;\"\n" +
                "                                                        valign=\"top\">\n" +
                "                                                        <td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\"\n" +
                "                                                            valign=\"top\"><a href=\"https://www.facebook.com/\"\n" +
                "                                                                            target=\"_blank\"><img alt=\"Facebook\"\n" +
                "                                                                                                 height=\"32\"\n" +
                "                                                                                                 src=\"img/facebook2x.png\"\n" +
                "                                                                                                 style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\"\n" +
                "                                                                                                 title=\"Facebook\"\n" +
                "                                                                                                 width=\"32\"/></a></td>\n" +
                "                                                        <td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\"\n" +
                "                                                            valign=\"top\"><a href=\"https://twitter.com/\" target=\"_blank\"><img\n" +
                "                                                                alt=\"Twitter\" height=\"32\" src=\"img/twitter2x.png\"\n" +
                "                                                                style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\"\n" +
                "                                                                title=\"Twitter\" width=\"32\"/></a></td>\n" +
                "                                                        <td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\"\n" +
                "                                                            valign=\"top\"><a href=\"https://instagram.com/\"\n" +
                "                                                                            target=\"_blank\"><img alt=\"Instagram\"\n" +
                "                                                                                                 height=\"32\"\n" +
                "                                                                                                 src=\"img/instagram2x.png\"\n" +
                "                                                                                                 style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\"\n" +
                "                                                                                                 title=\"Instagram\"\n" +
                "                                                                                                 width=\"32\"/></a></td>\n" +
                "                                                        <td style=\"word-break: break-word; vertical-align: top; padding-bottom: 0; padding-right: 5px; padding-left: 5px;\"\n" +
                "                                                            valign=\"top\"><a href=\"https://www.linkedin.com/\"\n" +
                "                                                                            target=\"_blank\"><img alt=\"LinkedIn\"\n" +
                "                                                                                                 height=\"32\"\n" +
                "                                                                                                 src=\"img/linkedin2x.png\"\n" +
                "                                                                                                 style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: none; display: block;\"\n" +
                "                                                                                                 title=\"LinkedIn\"\n" +
                "                                                                                                 width=\"32\"/></a></td>\n" +
                "                                                    </tr>\n" +
                "                                                    </tbody>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        </tbody>\n" +
                "                                    </table>\n" +
                "\n" +
                "                                    <div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:15px;padding-right:40px;padding-bottom:10px;padding-left:40px;\">\n" +
                "                                        <div style=\"line-height: 1.5; font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; color: #555555; mso-line-height-alt: 18px;\">\n" +
                "                                            <p style=\"font-size: 12px; line-height: 1.5; word-break: break-word; text-align: left; font-family: inherit; mso-line-height-alt: 18px; margin: 0;\">\n" +
                "                                                <span style=\"color: #95979c; font-size: 12px;\">Etiam quis tempus ex. Sed vitae ipsum suscipit, ultricies odio vitae, suscipit massa. Sed tempus ipsum eget diam aliquam maximus. Cras accumsan urna vel rutrum lobortis. Maecenas tristique purus vel ex tempor consequat. Curabitur dui massa, congue sed sem at, rhoncus imperdiet sem. Fusce ac orci fermentum, malesuada dolor a, cursus augue. Quisque porttitor sapien arcu, quis iaculis nisi faucibus eget. Vestibulum eu velit rhoncus, aliquam ante eget, tristique diam dui massa, congue sed sem at, rhoncus usce ac orci fermentum,.</span>\n" +
                "                                            </p>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\"\n" +
                "                                           role=\"presentation\"\n" +
                "                                           style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
                "                                           valign=\"top\" width=\"100%\">\n" +
                "                                        <tbody>\n" +
                "                                        <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "                                            <td class=\"divider_inner\"\n" +
                "                                                style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 25px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px;\"\n" +
                "                                                valign=\"top\">\n" +
                "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                       class=\"divider_content\" role=\"presentation\"\n" +
                "                                                       style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #555961; width: 100%;\"\n" +
                "                                                       valign=\"top\" width=\"100%\">\n" +
                "                                                    <tbody>\n" +
                "                                                    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "                                                        <td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
                "                                                            valign=\"top\"><span></span></td>\n" +
                "                                                    </tr>\n" +
                "                                                    </tbody>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        </tbody>\n" +
                "                                    </table>\n" +
                "\n" +
                "                                    <div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:20px;padding-right:40px;padding-bottom:30px;padding-left:40px;\">\n" +
                "                                        <div style=\"line-height: 1.2; font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; color: #555555; mso-line-height-alt: 14px;\">\n" +
                "                                            <p style=\"font-size: 12px; line-height: 1.2; word-break: break-word; text-align: left; font-family: inherit; mso-line-height-alt: 14px; margin: 0;\">\n" +
                "                                                <span style=\"color: #95979c; font-size: 12px;\">Copyright © 2020</span>\n" +
                "                                            </p>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
    }
}
