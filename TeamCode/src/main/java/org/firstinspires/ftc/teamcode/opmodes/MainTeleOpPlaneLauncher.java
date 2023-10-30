package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivetrain;

import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class MainTeleOpPlaneLauncher extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;
    Servo yourServo;
    boolean servoPositionIsZero = true;

    @Override
    public void runOpMode() {
        // Initialize your hardware components here
        yourServo = hardwareMap.servo.get("your_servo");

        // Set the initial position of the servo
        if (servoPositionIsZero) {
            yourServo.setPosition(0.0);  // 0 degrees
        } else {
            yourServo.setPosition(0.5);  // 90 degrees
        }

        waitForStart();

        while (opModeIsActive()) {
            // Check if the "A" button on gamepad2 is pressed
            if (gamepad2.a) {
                // Toggle the servo position
                if (servoPositionIsZero) {
                    yourServo.setPosition(0.5);  // 90 degrees
                } else {
                    yourServo.setPosition(0.0);  // 0 degrees
                }
                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
                sleep(500); // Add a delay to prevent rapid toggling
            }

            // Add other gamepad input checks as needed

            // Update telemetry (optional)
            telemetry.update();
        }
    }
}
