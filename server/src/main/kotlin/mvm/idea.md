structure

package info

```json
[
    {
        "repository": "kar-main",
        "info": [
            {
                "module": "test",
                "packages": [
                    {
                        "name": "test",
                        "code": "str f1 \"Hello, World!\"\ncall println",
                        "version": "1.0",
                        "help": {
                            "name": "test",
                            "arguments": [],
                            "info": "Prints hello world to stdout"
                        },
                        "dependencies": [
                            "kar-main:stdlib:println:1.0"
                        ]
                    }
                ]
            },
            {
                "module": "stdlib",
                "packages": [
                    {
                        "name": "readln",
                        "version": "1.0",
                        "code": "LIT S1 25\nSYSCALL\n",
                        "help": {
                            "name": "readln",
                            "arguments": [],
                            "info": "Reads the current input from stdin. And returns the string pointer to R2"
                        },
                        "dependencies": []
                    },
                    {
                        "name": "println",
                        "version": "1.0",
                        "code": "LIT S0 24\nMOV F1 S1\nSTRLEN S1\nADD R4 F1\nLIT G1 10\nLIT G2 0\nLIT G3 1\nSTORE G1 R4\nADD G3 R4\nSTORE G2 R4\nSYSCALL",
                        "help": {
                            "name": "println",
                            "arguments": [
                                {
                                    "name": "F1",
                                    "info": "The string to print to the screen"
                                }
                            ],
                            "info": "Writes a string to the screen with a new line"
                        },
                        "dependencies": []
                    }
                ]
            }
        ]
    }
]

```