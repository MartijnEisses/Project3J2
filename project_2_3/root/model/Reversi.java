package root.model;


import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Reversi extends Board {

    final double CORNERPLACE = 10;
    final double EDGEPLACE = 5;
    final double PLACENEXTCORNER = -10;
    double stonePosition[][] = new double[8][8];
    private int currentPlayer; // wit is true - zwart is false
    private int wit; // counter voor wit.
    private int zwart;// counter voor zwart.
    private boolean finished;


    public Reversi() {
        super(8, 8);
    }

    public static ArrayList<String> legalMoves(int[][] curBoard, int cp) {
        int totChanges = 0;
        int tempChanges = 0;
        int[][] bard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        int[][] bard2 = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        List<String> allMoves = new ArrayList<String>();
        boolean change = false;

        int checker1;
        int checker2;
        int curp = 1;
        int nCurp = 2;
        if (cp == 2) {
            curp = 2;
            nCurp = 1;
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                change = false;
                // totChanges = 0;
                if (curBoard[x][y] == 0) {
                    try {
                        if (curBoard[x + 1][y] == nCurp) {
                            checker1 = x + 1;
                            while (curBoard[checker1][y] == nCurp) {
                                checker1++;
                                // tempChanges++;

                            }

                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }

                    //tempChanges = 0;
                    try {
                        if (curBoard[x - 1][y] == nCurp) {
                            checker1 = x - 1;

                            while (curBoard[checker1][y] == nCurp) {

                                //tempChanges++;
                                checker1--;
                            }


                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }


                    tempChanges = 0;
                    try {
                        if (curBoard[x][y + 1] == nCurp) {
                            checker2 = y + 1;

                            while (curBoard[x][checker2] == nCurp) {
                                //tempChanges++;
                                checker2++;

                            }
                            if (curBoard[x][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    //tempChanges = 0;
                    try {
                        if (curBoard[x][y - 1] == nCurp) {
                            checker2 = y - 1;

                            while (curBoard[x][checker2] == nCurp) {
                                //tempChanges++;

                                checker2--;
                            }


                            if (curBoard[x][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;

                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    //tempChanges = 0;
                    try {
                        if (curBoard[x + 1][y + 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y + 1;

                            while (curBoard[checker1][checker2] == nCurp) {

                                checker1++;

                                //tempChanges++;

                                checker2++;


                            }


                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;

                            }

                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    //tempChanges = 0;
                    try {
                        if (curBoard[x - 1][y - 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y - 1;

                            while (curBoard[checker1][checker2] == nCurp) {
                                tempChanges++;

                                checker1--;
                                checker2--;


                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;

                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }

                    //tempChanges = 0;
                    try {
                        if (curBoard[x + 1][y - 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y - 1;

                            while (curBoard[checker1][checker2] == nCurp) {

                                checker1++;

                                //tempChanges ++;

                                checker2--;


                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    //tempChanges = 0;
                    try {
                        if (curBoard[x - 1][y + 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y + 1;

                            while (curBoard[checker1][checker2] == nCurp) {
                                //tempChanges++;

                                checker1--;
                                checker2++;
                            }


                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }//if statement equals(".")
        // for loop y
        //for loop x
        //tempChanges = 0;
        // if(change) { allMoves.add(y + "-" + x + "_" + totChanges);}
        return (ArrayList<String>) allMoves;
    }//class

    public boolean isInBounce(int x, int y) {
        if (x + 1 != 8 && x - 1 != -1 && y + 1 != 8 && y - 1 != -1) {
            return true;
        }
        return false;
    }

    public int[][] doMove(int[][] curBoard, int cp, int x, int y) {
        int[][] bard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        int[][] bard2 = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        boolean change = false;

        //fullWord = move.split("_");
        //int stenen = Integer.parseInt(fullWord[1]);
        int checker1;
        int checker2;
        int curp = 2;
        int nCurp = 1;
        if (cp == 1) {
            curp = 1;
            nCurp = 2;
        }
        if (curBoard[x][y] == 0) {
            try {
                if (curBoard[x + 1][y] == nCurp) {
                    checker1 = x + 1;

                    while (curBoard[checker1][y] == nCurp) {
                        // if (checker1 != -1 && checker1 != 8) {
                        System.out.println("BEN");
                        bard[checker1][y] = curp;
                        checker1++;
                        //}
                    }

                    if (curBoard[checker1][y] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        System.out.println("BEN_F");
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (curBoard[x - 1][y] == nCurp) {
                    checker1 = x - 1;
                    while (curBoard[checker1][y] == nCurp) {
                        System.out.println("BOV");
                        bard[checker1][y] = curp;
                        checker1--;
                    }

                    if (curBoard[checker1][y] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        System.out.println("BOV_F");
                        change = true;
                    }

                }
            }catch(ArrayIndexOutOfBoundsException e){}
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                try {
                    if (curBoard[x][y + 1] == nCurp) {
                        checker2 = y + 1;

                        while (curBoard[x][checker2] == nCurp) {
                            System.out.println("RE");

                            bard[x][checker2] = curp;
                            checker2++;
                        }

                        // }
                        if (curBoard[x][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("RE_F");
                        }
                    }

                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (curBoard[x][y - 1] == nCurp) {
                        checker2 = y - 1;

                        while (curBoard[x][checker2] == nCurp) {
                            // if (checker2 != 8 && checker2 != -1) {
                            System.out.println("LI");
                            bard[x][checker2] = curp;
                            checker2--;
                        }
                        // }
                        if (curBoard[x][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("LI_F");

                        }

                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (curBoard[x + 1][y + 1] == nCurp) {
                        checker1 = x + 1;
                        checker2 = y + 1;

                        while (curBoard[checker1][checker2] == nCurp) {
                            //  if (checker1 != -1 && checker1 != 8 && checker2 != -1 && checker2 != 8) {
                            bard[checker1][checker2] = curp;
                            System.out.println("SCH-BEN-REC");
                            checker1++;
                            checker2++;

                        }

                        // }
                        if (curBoard[checker1][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("SCH-BEN-REC_F");

                        }
                    }

                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (curBoard[x - 1][y - 1] == nCurp) {
                        checker1 = x - 1;
                        checker2 = y - 1;

                        while (curBoard[checker1][checker2] == nCurp) {
                            // if (checker1 != -1 && checker1 != 8 && checker2 != -1 && checker2 != 8) {
                            System.out.println("SCH-BOV-LIN");
                            bard[checker1][checker2] = curp;
                            checker1--;
                            checker2--;

                        }
                        // }

                        if (curBoard[checker1][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("SCH-BOV-LIN_f");

                        }
                    }

                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (curBoard[x + 1][y - 1] == nCurp) {
                        checker1 = x + 1;
                        checker2 = y - 1;

                        while (curBoard[checker1][checker2] == nCurp) {
                            // if (checker1 != -1 && checker1 != 8 && checker2 != -1 && checker2 != 8) {
                            System.out.println("SCH-BOV-REC");
                            bard[checker1][checker2] = curp;
                            checker1++;
                            checker2--;

                        }
                        // }

                        if (curBoard[checker1][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("SCH-BOV-REC_F");
                        }
                    }

                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (curBoard[x - 1][y + 1] == nCurp) {
                        checker1 = x - 1;
                        checker2 = y + 1;

                        while (curBoard[checker1][checker2] == nCurp) {
                            //  if (checker1 != -1 && checker1 != 8 && checker2 != -1 && checker2 != 8) {
                            System.out.println("SCH-BOV-LIN");
                            bard[checker1][checker2] = curp;
                            checker1--;
                            checker2++;

                        }

                        if (curBoard[checker1][checker2] == curp) {
                            bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                            change = true;
                            System.out.println("SCH-BOV-LIN_F");

                        }
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }
                if (change == true) {
                    bard2[x][y] = curp;
                    return bard2;
                }

                System.out.println("Move is not valid Do MOVE TEST");
                //for (int i = 0; i < curBoard.length; i++) {
                //    for (int j = 0; j < curBoard[1].length; j++) {
                //        System.out.print(curBoard[i][j]);
                //    }
                //   System.out.print("\n");
                //}
                //System.out.println(x+"x+y"+y);
                // System.out.println(cp);
                // System.out.println(legalMoves(curBoard, cp));
                return curBoard;
              }
                return curBoard;
            }
    }
