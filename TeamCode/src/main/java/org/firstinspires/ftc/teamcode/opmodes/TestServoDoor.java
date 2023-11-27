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
    private SimpleServo servo1, servo2;

    // Declare our gamepad
    private GamepadEx Gamepad1;

    @Override
    public void runOpMode() {
        // Initialize the servos to a position
        servo1 = new SimpleServo(hardwareMap, "servo1", 0.0, 1.0);
        servo2 = new SimpleServo(hardwareMap, "servo2", 0.0, 1.0);

        // Initialize the gamepad
        Gamepad1 = new GamepadEx(gamepad1);

        // Wait for the start button to be pressed
        waitForStart();

        servo1.setPosition(0);

        while (opModeIsActive()) {
            if (gamepad1.a) {
                // if the position is 90 make it 0
                if (servo1.getPosition() == 0.2) {
                    servo1.setPosition(0);
                    //servo2.setPosition(0);
                } else {//if (servo1.getPosition() == 0.25) { // else set the position of the servos to 90
                    servo1.setPosition(0.2);
                    //servo2.setPosition(45);
                }
                sleep(500);
            }
            // Give hardware a chance to catch up
            idle();
        }
    }
}
