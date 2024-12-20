import styled from "styled-components";

export const ExitButton = styled.div`
  margin: 16px;
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100px;
  height: 30px;
  background-color: rgba(0, 0, 0, 0);
  font-size: 12px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.text.secondary};
  border: 1px solid ${({ theme }) => theme.colors.background.secondary};
  border-radius: 5px;
`;
