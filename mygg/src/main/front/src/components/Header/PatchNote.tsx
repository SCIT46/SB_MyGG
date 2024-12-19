import { useState } from "react";
import styled from "styled-components";

const PatchNoteBtn = styled.div`
  color: ${({ theme }) => theme.colors.text.primary};
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
  background-color: ${({ theme }) => theme.colors.background.white};
  width: 88px;
  border: 1px solid black;
  border-radius: 7px;
`;

const PatchNoteSpan = styled.div`
  padding: 5px;
  color: ${({ theme }) => theme.colors.text.primary};
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.15s ease;
  &:hover {
  }
`;

//더미 데이터
const patchNotes = ["14-22", "14-21", "14.20", "14.19", "14.18"];

//헤더 내의 패치노트 박스 컴포넌트
export default function PatchNote() {
  const [isPatchNoteClicked, setIsPatchNoteClicked] = useState<boolean>(false);

  return (
    <>
      <PatchNoteBtn onClick={() => setIsPatchNoteClicked((prev) => !prev)}>
        패치노트
      </PatchNoteBtn>
      {/* patchnoteBtn 컴포넌트가 클릭시 patchNoteBox 랜더링 & Patchnote 데이터를 이용해 버튼 생성 */}
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
