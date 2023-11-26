package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Dual Servo Control", group="23925 Test")
public class TeleOpFIXServo extends LinearOpMode {

    // Declare our servos
    private SimpleServo servo1, servo2;

    // Declare our gamepad
    private GamepadEx gamepad2;

    @Override
    public void runOpMode() {
        // Initialize the servos to a position
        servo1 = new SimpleServo(hardwareMap, "servo1", 0.0, 1.0);
        servo2 = new SimpleServo(hardwareMap, "servo2", 0.0, 1.0);

        // Initialize the gamepad
        gamepad2 = new GamepadEx(gamepad1);

        // Wait for the start button to be pressed
        waitForStart();

        while (opModeIsActive()) {
            // Get the y value of the left stick on gamepad 2
            double position = gamepad2.getLeftY();

            // Set the position of the servos
            servo1.setPosition(position);
            servo2.setPosition(position);

            // Give hardware a chance to catch up
            idle();
        }
    }
}
