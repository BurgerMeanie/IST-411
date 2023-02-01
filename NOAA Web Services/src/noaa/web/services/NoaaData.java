
package noaa.web.services;

/*
Project: Lab 3
Purpose Details: NOAA Web Services
Course: IST 411
Author: Team 1 (Kelly Bergamini, William Allwein, Hun Bae, Phillip Berry, 
    Allea McFarlane, Jason Fang)
Date Developed: 1/31/2023
Last Date Changed:
Revision: 1
*/

class NoaaData {
   private Metadata metadata;
    private Results results[];
    
    public Metadata getMetaData() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    
    public Results[] getResults() {
        return results;
    }
    public void setResults(Results[] results) {
        this.results = results;
    }
}
