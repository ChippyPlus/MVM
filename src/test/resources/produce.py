import os
import google.generativeai as genai

genai.configure(api_key="AIzaSyAk8M_DaSkat3sxYk811Bc2eGM7ZaPGNFU")

# Create the model
generation_config = {
    "temperature": 1,
    "top_p": 0.95,
    "top_k": 40,
    "max_output_tokens": 8192,
    "response_mime_type": "text/plain",
}

model = genai.GenerativeModel(
    model_name="gemini-1.5-pro",
    generation_config=generation_config,
)

chat_session = model.start_chat(
    history=[
    ]
)


def main():
    with open("/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/archive.zip.txt",
              'rb+') as f: content = f.read()
    response = chat_session.send_message(
        f"Hi gemini I would like you to explain what you see in this file\n```zipFile\n{content}\n```\n")
    print(response.text)
    while True:
        print(chat_session.send_message(input(" > ")).text)


if __name__ == "__main__":
    main()
