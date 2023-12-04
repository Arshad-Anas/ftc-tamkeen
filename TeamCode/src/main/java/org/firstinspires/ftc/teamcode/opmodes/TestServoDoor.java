package org.firstinspires.ftc.teamcode.opmodes;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group="23925 Test")
public class TestServoDoor extends LinearOpMode {

    // Declare our servos
    private Servo servo1, servo2;

    // Declare our gamepad
    private GamepadEx Gamepad1;

    @Override
    public void runOpMode() {
        // Initialize the servos to a position
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        // Initialize the gamepad
        Gamepad1 = new GamepadEx(gamepad1);

        // Wait for the start button to be pressed
        waitForStart();

        servo1.setPosition(0);
        boolean position = true;
        while (opModeIsActive()) {
            if (gamepad1.a && position) {
                if (servo1.getPosition() == 0) {
                    setPosition(.475);
                } else {
                    setPosition(0);
                }
//                position = false;
            }

//            if (!gamepad1.a) {
//                position = true;
//            }
            // Give hardware a chance to catch up
            idle();
        }
    }

    public void setPosition(double position) {
        servo1.setPosition(position);
        servo2.setPosition(1-position);
    }
}
