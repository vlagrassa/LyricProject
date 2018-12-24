public class LyricSlice {
    LyricCoords coords;

    public LyricSlice(Integer s, Integer e) {
        coords = new LyricCoords(s, e);
    }

    public LyricCoords getCoords() {
        return coords;
    }

    public LyricCoords setCoords(LyricCoords newcoords) {
        LyricCoords oldcoords = coords;
        coords = newcoords;
        return oldcoords;
    }

    public Integer setStart(Integer newstart) {
        Integer oldstart = coords.getStart();
        if (newstart < 0) {
            return coords.setStart(0);
        }
        else if (newstart > coords.getEnd()) {
            Integer oldend = coords.getEnd();
            coords.setEnd(newstart);
            return coords.setStart(oldend);
        }
        else {
            return coords.setStart(newstart);
        }
        // return oldstart;
    }

    // public Integer setEnd(Integer newend) {
    //     Integer oldend = end;
    //     if (newend > reference.length()) {
    //         end = reference.length();
    //     }
    //     else if (newend < start) {
    //         end = start;
    //         setStart(newend);
    //     } else {
    //         end = newend;
    //     }
    //     return oldend;
    // }
}

class LyricCoords {
    private Integer start;
    private Integer end;
    private String reference;

    public LyricCoords(Integer s, Integer e, String ref) {
        start = s;
        end = e;
        reference = ref;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer setStart(Integer newstart) {
        Integer oldstart = start;
        start = newstart
        return oldstart;
    }

    public Integer setEnd(Integer newend) {
        Integer oldend = end;
        end = newend;
        return oldend;
    }

    public Integer moveStart(Integer offset) {
        return setStart(start + offset);
    }

    public Integer moveEnd(Integer offset) {
        return setEnd(end + offset);
    }

    public void setCoords(Integer s, Integer e) {
        setStart(s);
        setEnd(e);
    }

    public void moveCoords(Integer offsetS, Integer offsetE) {
        setCoords(start + offsetS, end + offsetE);
    }
}