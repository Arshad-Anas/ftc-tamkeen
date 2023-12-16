package org.firstinspires.ftc.teamcode.opmodes;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot;

@TeleOp(group = "23925 TeleOp")
public class MainTeleOpMeacunum extends LinearOpMode {

    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        waitForStart();

        // Control loop:
        while (opModeIsActive()) {
            robot.readSensors();
            // Read sensors + gamepads:
            gamepadEx1.readButtons();
            gamepadEx2.readButtons();

            boolean aPressed = gamepadEx1.wasJustPressed(A); // this will only be true for one loop

            // Field-centric drive dt with control stick inputs:
            robot.drivetrain.run(
                    gamepadEx1.getLeftX(),
                    gamepadEx1.getLeftY(),
                    gamepadEx1.getRightX()
            );

            mTelemetry.update();
        }
        robot.interrupt();
    }
}