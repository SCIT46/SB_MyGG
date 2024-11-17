import { useState } from "react";
import styled from "styled-components";

const PatchNoteBtn = styled.div`
  color: ${({ theme }) => theme.colors.textBlack};
  position: relative;
  margin-left: auto;
  margin-right: 10px;
  padding: 12px;
  user-select: none;
  border-radius: 10px;

  cursor: pointer;
  &:hover {
  }
`;

const PatchNoteBox = styled.div`
  padding: 15px;
  position: absolute;
  display: flex;
  flex-direction: column;
  gap: 3px;
  top: 47px;
  right: 10px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  width: 88px;
  border: 1px solid black;
  border-radius: 7px;
`;

const PatchNoteSpan = styled.div`
  padding: 5px;
  color: ${({ theme }) => theme.colors.textBlack};
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.15s ease;
  &:hover {
  }
`;

const patchNotes = ["14-22", "14-21", "14.20", "14.19", "14.18"];

export default function PatchNote() {
  const [isPatchNoteClicked, setIsPatchNoteClicked] = useState<boolean>(false);

  return (
    <>
      <PatchNoteBtn onClick={() => setIsPatchNoteClicked((prev) => !prev)}>
        패치노트
      </PatchNoteBtn>
      {isPatchNoteClicked && (
        <PatchNoteBox>
          {patchNotes.map((note, index) => (
            <PatchNoteSpan
              onClick={() =>
                window.open(
                  "https://www.leagueoflegends.com/ko-kr/news/game-updates/patch-14-22-notes/"
                )
              }
            >
              14.22 -
            </PatchNoteSpan>
          ))}
        </PatchNoteBox>
      )}
    </>
  );
}
