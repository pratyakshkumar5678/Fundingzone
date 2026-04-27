<!DOCTYPE html>
<html>
<head>
    <title>Account Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        p {
            color: #666;
            margin-bottom: 20px;
        }

        #countdown {
            font-size: 24px;
            color: #ff0000;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        var count = 3;
        var countdown = setInterval(function() {
            document.getElementById("countdown").innerHTML = count;
            count--;
            if (count < 0) {
                clearInterval(countdown);
                closeWindow(); // Panggil fungsi untuk menutup jendela
            }
        }, 1000);

        function closeWindow() {
            window.close(); // Menutup jendela saat dipanggil oleh script JavaScript
        }
    </script>
</head>
<body>
    <div class="container">
        <?php
        require_once 'connection.php';
        
        // Extract the verification token from the URL
        $verificationToken = isset($_GET['token']) ? $_GET['token'] : null;
        $error_message = "";
        
        if ($verificationToken) {
            // Validate the token and check expiration
            $result = mysqli_query($con, "SELECT * FROM user WHERE token = '$verificationToken'");
        
            if (mysqli_num_rows($result) > 0) {
                // Token is valid and not expired, activate the user's account
                mysqli_query($con, "UPDATE user SET accountstate = 'ACTIVE', token = NULL WHERE token = '$verificationToken'");
                
                // Display a success message
                $error_message = "Account successfully activated. You can now log in.";
        
            } else {
                // Token is invalid, expired, or both
                $error_message = "Invalid token. Please check your email or contact support.";
            }
    
        } else {
            // Token is not provided
            $error_message = "Nothing to see here. Move along.";
        }
        ?>

        <h1><?php echo $error_message ?></h1>
        <p>Regards: Admin of er-apps</p>
        
        <p>Closing this tab in <span id="countdown">3</span> seconds...</p>
        <button onclick="closeWindow()">Close Browser</button>
        <?php exit()?>
    </div>
</body>
</html>