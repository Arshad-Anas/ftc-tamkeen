package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

@TeleOp(group = "23925 TeleOp")
public class MainTeleOpMeacunum extends LinearOpMode {

    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;
    List<LynxModule> hubs;

    DcMotor hangerMotor;
    Servo servo1, servo2;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        // Initialize hardware components from TestHanger
        hubs = hardwareMap.getAll(LynxModule.class);
        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");

        // Initialize servos from TestServoDoor
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        waitForStart();

        // Control loop:
        while (opModeIsActive()) {
            robot.readSensors();
            // Read sensors + gamepads:
            gamepadEx1.readButtons();
            gamepadEx2.readButtons();

            boolean aPressed = gamepadEx1.wasJustPressed(GamepadEx.GamepadKeys.Button.A);

            // Field-centric drive dt with control stick inputs:
            robot.drivetrain.run(
                    gamepadEx1.getLeftX(),
                    gamepadEx1.getLeftY(),
                    gamepadEx1.getRightX()
            );

            // Hanger motor control
            hangerMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);

            // Servo control
            if (gamepad1.a && aPressed) {
                if (servo1.getPosition() == 0) {
                    setPosition(.475);
                } else {
                    setPosition(0);
                }
            }

            mTelemetry.update();
        }
        robot.interrupt();
    }

    public void setPosition(double position) {
        servo1.setPosition(position);
        servo2.setPosition(1 - position);
    }
}
