package com.inube.security.util;

public class MailTemplateUtil {
    private MailTemplateUtil() {
        // Evita instanciación
    }

    public static final String VERIFY_ACCOUNT_TEMPLATE = """
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <title>Verifica tu cuenta</title>
        </head>
        <body style="
            margin:0;
            padding:0;
            font-family: Arial, sans-serif;
            background-color:#f5f5f5;
        ">

            <table width="100%%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">

                        <table width="600" cellpadding="0" cellspacing="0"
                               style="
                               background:#ffffff;
                               margin:40px auto;
                               border-radius:12px;
                               overflow:hidden;
                               box-shadow:0 4px 12px rgba(0,0,0,0.08);
                               ">

                            <tr>
                                <td style="
                                    background:#111827;
                                    color:white;
                                    text-align:center;
                                    padding:30px;
                                    font-size:28px;
                                    font-weight:bold;
                                ">
                                    INUBE
                                </td>
                            </tr>

                            <tr>
                                <td style="padding:40px; color:#374151;">

                                    <h2 style="margin-top:0; font-size:24px;">
                                        Bienvenido a INUBE 🚀
                                    </h2>

                                    <p style="
                                        font-size:16px;
                                        line-height:1.6;
                                    ">
                                        Gracias por registrarte.
                                        Para activar tu cuenta,
                                        verifica tu correo dando clic
                                        en el siguiente botón.
                                    </p>

                                    <div style="
                                        text-align:center;
                                        margin:40px 0;
                                    ">
                                        <a href="%s"
                                           style="
                                           background:#111827;
                                           color:white;
                                           padding:14px 30px;
                                           text-decoration:none;
                                           border-radius:8px;
                                           font-size:16px;
                                           font-weight:bold;
                                           display:inline-block;
                                           ">
                                            Verificar cuenta
                                        </a>
                                    </div>

                                    <p style="
                                        font-size:14px;
                                        color:#6b7280;
                                    ">
                                        Este enlace expirará en 24 horas.
                                    </p>

                                </td>
                            </tr>

                            <tr>
                                <td style="
                                    background:#f9fafb;
                                    padding:20px;
                                    text-align:center;
                                    font-size:12px;
                                    color:#9ca3af;
                                ">
                                    © 2026 INUBE. Todos los derechos reservados.
                                </td>
                            </tr>

                        </table>

                    </td>
                </tr>
            </table>

        </body>
        </html>
        """;

    public static final String CONTACT_TEMPLATE = """
    <!DOCTYPE html>
    <html lang="es">
    <body style="font-family: Arial; background:#f5f5f5;">

        <table width="100%%">
            <tr>
                <td align="center">

                    <table width="600"
                           style="background:white;
                           margin:40px auto;
                           border-radius:12px;
                           padding:40px;">

                        <tr>
                            <td align="center">
                                <h1>INUBE 📩</h1>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <h2>Hola %s</h2>

                                <p>
                                    Hemos recibido tu mensaje correctamente.
                                </p>

                                <p>
                                    Nuestro equipo te responderá pronto.
                                </p>

                                <hr>

                                <p>
                                    Gracias por contactarnos.
                                </p>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
        </table>

    </body>
    </html>
    """;

    public static final String REPLY_TEMPLATE = """
    <!DOCTYPE html>
    <html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Respuesta INUBE</title>
    </head>
    <body style="
        margin:0;
        padding:0;
        font-family: Arial, sans-serif;
        background-color:#f5f5f5;
    ">

        <table width="100%%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">

                    <table width="600" cellpadding="0" cellspacing="0"
                           style="
                           background:#ffffff;
                           margin:40px auto;
                           border-radius:12px;
                           overflow:hidden;
                           box-shadow:0 4px 12px rgba(0,0,0,0.08);
                           ">

                        <tr>
                            <td style="
                                background:#111827;
                                color:white;
                                text-align:center;
                                padding:30px;
                                font-size:28px;
                                font-weight:bold;
                            ">
                                INUBE
                            </td>
                        </tr>

                        <tr>
                            <td style="padding:40px; color:#374151;">

                                <h2>Hola %s 👋</h2>

                                <p>
                                    Hemos atendido tu solicitud.
                                </p>

                                <div style="
                                    background:#f9fafb;
                                    border-radius:8px;
                                    padding:20px;
                                    margin:20px 0;
                                ">
                                    %s
                                </div>

                                <p>
                                    Gracias por contactarnos.
                                </p>

                            </td>
                        </tr>

                        <tr>
                            <td style="
                                background:#f9fafb;
                                padding:20px;
                                text-align:center;
                                font-size:12px;
                                color:#9ca3af;
                            ">
                                © 2026 INUBE
                            </td>
                        </tr>

                    </table>

                </td>
            </tr>
        </table>

    </body>
    </html>
    """;

    public static String cuentaInactivadaTemplate(
            String nombre
    ) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; background:#f4f6f8; padding:20px;">
                <div style="max-width:600px; margin:auto; background:white; border-radius:10px; padding:30px; box-shadow:0 2px 10px rgba(0,0,0,0.1);">
                    
                    <h2 style="color:#dc3545; text-align:center;">
                        Cuenta inactivada
                    </h2>

                    <p>Hola <strong>%s</strong>,</p>

                    <p>
                        Te informamos que tu cuenta ha sido
                        <strong>inactivada temporalmente</strong>.
                    </p>

                    <p>
                        Si consideras que esto es un error,
                        por favor comunícate con
                        <strong>soporte técnico</strong>.
                    </p>

                    <div style="text-align:center; margin-top:30px;">
                        <a href="soporte@inube.com"
                           style="background:#0d6efd; color:white; padding:12px 20px; text-decoration:none; border-radius:5px;">
                           Contactar soporte
                        </a>
                    </div>

                    <hr style="margin-top:30px;">

                    <p style="font-size:12px; color:gray;">
                        Este correo fue generado automáticamente.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(nombre);
    }

    public static String cuentaActivadaTemplate(
            String nombre
    ) {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; background:#f4f6f8; padding:20px;">
                <div style="max-width:600px; margin:auto; background:white; border-radius:10px; padding:30px; box-shadow:0 2px 10px rgba(0,0,0,0.1);">

                    <h2 style="color:#198754; text-align:center;">
                        Cuenta reactivada
                    </h2>

                    <p>Hola <strong>%s</strong>,</p>

                    <p>
                        Tu cuenta ha sido
                        <strong>reactivada exitosamente</strong>.
                    </p>

                    <p>
                        Ya puedes volver a iniciar sesión.
                    </p>

                    <hr style="margin-top:30px;">

                    <p style="font-size:12px; color:gray;">
                        Este correo fue generado automáticamente.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(nombre);
    }


    public static String buildContactTemplate(String name) {
        return CONTACT_TEMPLATE.formatted(name);
    }

    public static String buildVerificationTemplate(String link) {
        return VERIFY_ACCOUNT_TEMPLATE.formatted(link);
    }

    public static String buildReplyTemplate( String nombre, String respuesta ) {
        return REPLY_TEMPLATE.formatted(nombre, respuesta);
    }
}
