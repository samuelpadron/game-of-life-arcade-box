# CONTROLS
| KEY | Description |
| ------ | ----------- |
| JOYSTICK_UP   | Moves cursor upwards. |
| JOYSTICK_DOWN   | Moves cursor downwards. |
| JOYSTICK_LEFT   | Moves cursor to the left |
| JOYSTICK_RIGHT   | Moves cursor to the right |
| YELLOW   | **Tap**: Plays/pauses the game. **Hold**: Erases all patterns in the screen. |
| BLUE   | Places selected pattern. |
| LEFT_BLACK   | Selects previous pattern. |
| RIGHT_BLACK   | Selects next pattern. |
| TOP_PINK   | Increases game speed. |
| BOTTOM_PINK | Decreases game speed. |

# RGB Matrix

```
sudo ./demo -D0 --led-slowdown-gpio=4 --led-no-hardware-pulse --led-chain=4 --led-pixel-mapper=U-mapper;Rotate:180
```

