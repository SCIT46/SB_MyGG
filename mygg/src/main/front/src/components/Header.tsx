import { Link } from "react-router-dom";
import styled from "styled-components";

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  width: 100vw;
  height: 50px;
  background-color: #797980;
`;

const LinkBox = styled.div`
  padding: 12px;
  margin-left: 5px;
  margin-right: 5px;
  margin-bottom: 2px;
  border-radius: 10px;
  color: white;
  transition: background-color 0.2s ease;
  &:hover {
    background-color: #5250dd;
  }
`;

const LogoImg = styled.img`
  height: 50px;
  margin-right: 5px;
`;

export default function Header() {
  return (
    <HeaderContainer>
      <Link to={"/"}>
        <LogoImg src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAARMAAAC3CAMAAAAGjUrGAAAAgVBMVEVNjP7///////2lw/pKiv5Dh/71+v9MjP1Qj/1FiP2qxfxChv08g/v///z2+v37/v6wyvtgmPtalPpXkvxmm/vv9f3c6PrO3/zD1/yXuvrJ2/t7qPqNs/twovtUkPvq8f7T4vyRt/yGr/yfv/u1zfvf7Py70vt8qfkzf/1wofyTuPtE79VxAAAEcklEQVR4nO3Ya3faOBAGYFtIGCHhC9jmbgwEmvD/f+BqRrIxTbt79uRD6t33aU+ChaqeGUvyyFEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwPyGldgbXSume/NRbq1+1/jTGuEkTHbfzyWVlQkhyn89729LoJHn2VqY8z+fn0qjBEEk3RmP+C2mRqthlgtVnf/vVPO4JEZ8upu+tm7yKqW9czWfP8E25cWMsRCxul89TaHRkHguKnP/UBcWpJvGr3ISJYi5TThT/nV66qaJzPwC1i7YYe1KSNUXST4r0qH+RE3H10ZurGLQuuuZoOEYspsdxrx+165eID2taypATH6fgm58W1Nk8uibfKsTFRZ+Y3bOnT8rqu8P6Cn0Oqag366mPqNYhJ7t5Tm7Crx63yorUd7ltNrWfPjSG2YbstZtdRR9O++8O6ytkxFGKzcoaa84p3/yHVhMKcmuVY+ya0yBpTi38fuE622Ur2hXvvY3fYt4kNe8r8TbuJ7LeLij63PKVKad0VcuQEx+a3vOSWrlpklHOdoa3UKkPJozBs2RrI9qH9XLvP4yWbekO32z3VPHhlXaYk2hJcykrIn2lLyvfV8pIux/0q6VMbaxvdZWb5ObR0hSuOPRzXfN+cHjJiSwzd+E2WbURYWOJZDLrRLOKcnKUlKvZs3m0Gt4Jlv213zsmXU4k3Xm75ekxk6am1j1NAfWWTr2sWXHKaGbI5Baa09Z+X1BftKIos6a/Nht+xrz7nBheBYeMH0ImUvys+eCc3PtiZLWk7yveW5JT95w+jTcnMw6r7Fe/5bCvdtKVIaE4jWNXiJg2fHjNScPbDS0W6XIS3Eabk8TS/iHmfQB8z+MfNtSxovspWkULhicMddb5yaHLLOFEiIPrkCSta+XtZT3anLi1QgFkKzm4jNOV+bm2P1Fhqi/0cfFBE8UdlM2ZJ4ThrVfcEkmtUr6v6TJXf/ff/tHkkafCbWncY0ObnKfFLtRsz8XT+qT5J0xVur3DpaSgUk3cld5z57XkLdlypsR+xGWb8TV6Oi+T5lD7THyEmk1kjsiq3Q8dJfRc4WZ3TJw01s6uKfc+UoHC/+x0kNaW9wVXeCNOSaTLrDu4hV/CFSD+vPN4t54LUH7s6SRQh05pOPiItaLvujGmWZhfjzHnJFKH4emfopTduwJfs3HZqu9uAelIF1X8cgSu6FVJ0o0huub1yF8rmUM6fH+yoxwMc8KojnFryiXlNHzbMt2H1yqHbDhG2/zm/xoNVbZ9PNWDay+3cbjQnzmRTeZCpTJGJ29ZWGoiXhe6OyiFMShhWZ6Me5YQqY53uv3T9tH4NKicQh7ME/0QcR6OycW8pgzd7sfB81ZqHkOk9WSpxp8St2NIY+QsMs9X7nzEG76sd4fdPgPKmCTRxryG3o0x3sLks9fj/T8c93/z5ZhfEQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPxbfwEzxDT/aWVErgAAAABJRU5ErkJggg==" />
      </Link>
      <Link to={"item"}>
        <LinkBox>아이템</LinkBox>
      </Link>
      <Link to={"champion"}>
        <LinkBox>챔피온</LinkBox>
      </Link>
      <Link to={"search/123"}>
        <LinkBox>플레이어</LinkBox>
      </Link>
    </HeaderContainer>
  );
}
